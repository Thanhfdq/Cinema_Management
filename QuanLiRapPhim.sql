create database Duan1_QLRP
go
use  Duan1_QLRP
go

--ham tao khoa chinh tu dong theo mau
CREATE FUNCTION AUTO_IDNV()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MaNV) FROM NhanVien) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaNV, 3)) FROM NhanVien
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NV00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'NV0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END

--bang nhan vien
create table NhanVien(
	MaNV nvarchar(10) primary key constraint idnv default dbo.AUTO_IDNV(), /* Pk */
	HoTen nvarchar(100) not null,
	GioiTinh bit not null default 1,
	NgaySinh date not null,
	NgayVaoLam date not null DEFAULT getdate(),
	ChucVu nvarchar(100) not null,
	Luong int not null check (luong>0),
	Hinh nvarchar(100),
	GhiChu nvarchar(200),
	MatKhau nvarchar(50)
)

--bang phim
create table Phim(
	MaPhim nvarchar(10) primary key not null,	/* PK */
	TenPhim nvarchar(200) not null,
	TheLoai nvarchar(100) not null,
	ThoiLuong int not null check(thoiluong >0),
	QuocGia nvarchar(50),
	Hinh nvarchar(100),
	GhiChu nvarchar(200)
)

-- bang thuc don
create table ThucDon(
	MaMon int IDENTITY(1,1) primary key, /* Pk */
	TenMon nvarchar(200) not null, 
	DonGia Float not null check(DonGia >0),
	DonViTinh nvarchar(50),
	Loai nvarchar(50) not null,
	Hinh nvarchar(100),
	GhiChu nvarchar(200),
)

--bang phong chieu
create table PhongChieu(
	MaPhong nvarchar(10) primary key not null, /* Pk */
	SoluongGhe int not null,
	DienTich nvarchar(50) not null,
	MayChieu nvarchar(50) not null,
	AmThanh nvarchar(50) not null,
	TinhTrang nvarchar(200) default N'Mới',
	GhiChu nvarchar(200),
	check(SoluongGhe > 0 and DienTich > 0)
)

--bang ghe
create table Ghe(
	MaPhong nvarchar (10) not null, /* Fk  */
	MaGhe nvarchar (10) not null, /*  PK */
	Gia Float not null default 10000.0,
	constraint pk_gh primary key (MaGhe,MaPhong),
	constraint fk_gh_ph FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong) on delete cascade
)

--bang hoa don
create table HoaDon(
	MaHD int IDENTITY(1,1) primary key,  /* Pk */
	MaNV nvarchar(10) not null, /* Fk */
	NgayLap date not null DEFAULT getdate(),
	constraint fk_hd_nv FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) on update cascade
)

--bang hoa don ct
create table HoaDonCT(
	MaHD int  not null, /* PK Fk */
	MaMon int not null, /* PK Fk */
	SoLuong int not null
	constraint pk_hdct primary key (MaHD,MaMon),
	constraint fk_hdct_td FOREIGN KEY (MaMon) REFERENCES Thucdon(MaMon),
	constraint fk_hdct_hd FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
	check(Soluong > 0)
)

--bang lich chieu
create table LichChieu(
	MaLichChieu int IDENTITY(1,1) primary key not null, /* PK */
	MaPhim nvarchar(10) not null,	/* Fk */
	GioChieu int not null,
	NgayChieu date not null DEFAULT getdate(),
	MaPhong nvarchar(10) not null/* Fk */
	constraint fk_lc_ph FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim) on update cascade,
	constraint fk_lc_pc FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong) on update cascade,
	check(GioChieu >= 0 and GioChieu < 24)
)

--bang ve
create table Ve(
	MaVe nvarchar(10) primary key not null, /* Pk */
	MaNV nvarchar(10) not null, /* Fk */
	NgayLap date not null  DEFAULT getdate(),
	MaGhe nvarchar(10) not null, /* Fk */
	MaPhong nvarchar(10) not null, /* Fk */
	MaPhim nvarchar(10) not null, /* Fk */
	constraint fk_ve_nv FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) on update cascade,	
	constraint fk_ve_gh FOREIGN KEY (MaGhe, MaPhong) REFERENCES Ghe(MaGhe, MaPhong) on update cascade,
	constraint fk_ve_ph FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim) on update cascade
)


-- Nhập Dữ Liệu Bảng Nhân Viên
Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Hồ Văn Khánh',1,'2003-10-22','2022-05-23',N'Nhân Viên Đặt vé ',3000000,'0000');
 Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Đồng Quốc Thành',1,'2003-05-12','2021-11-12',N'Quản Lí Rạp ',6000000,'0000');
 Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Đặng Quang Huy',1,'2003-09-18','2022-07-22',N' Nhân Viên Quản lí Phim ',3000000,'0000');
 Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Trịnh Thế Qúy',1,'2003-02-13','2022-01-08',N'Nhân Viên Bán Hàng' ,3000000,'0000');
 Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Phan Diệu Linh',0,'2003-12-24','2022-03-02',N'Nhân Viên Bán Hàng',3000000,'0000');
 Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Trần Văn Hiệp',1,'2003-09-21','2022-05-23',N'Nhân Viên Soát Vé',3000000,'0000');
Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Phạm Ngọc Sang',1,'2003-04-15','2022-03-17',N'Nhân Viên Soát Vé',3000000,'0000');
Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Phạm Thị Mỹ Lệ',0,'2003-10-22','2022-03-03',N'Nhân Viên Đặt Vé',3000000,'0000');
Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Nguyễn Thị Hồng',0,'2002-08-17','2022-09-12',N'Nhân Viên Bán Hàng',3000000,'0000');
Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Nguyễn Văn Huy',1,'2003-03-22','2022-05-23',N'Nhân Viên Đặt Vé',3000000,'0000');
Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Phạm Thị Ái Hoanh',0,'2002-11-12','2022-08-26',N'Nhân Viên Bán Hàng',3000000,'0000');
Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Lê Minh Trọng',1,'2003-09-26','2022-05-06',N' Nhân Viên Quản Lí Phim',3000000,'0000');
Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Nguyễn Thị Kiều Diễm',0,'2003-12-04','2022-10-19',N'Nhân Viên Đặt Vé',3000000,'0000');
Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Hoàng Thị Diễm Phương',0,'2001-02-02','2022-10-22',N'Nhân Viên Đặt Vé',3000000,'0000');
Insert into NhanVien(HoTen,GioiTinh,NgaySinh,NgayVaoLam,ChucVu,Luong,MatKhau) values 
(N'Trần Đức Tiến',0,'2003-09-18','2022-09-12',N'Nhân Viên Bán Hàng',3000000,'0000');

-- Nhập Dữ Liệu Bảng Phim 
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P001',N'Bỗng Dưng Trúng Số',N'Hài Hước',120 ,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P002',N'Giáng Long Đại Sư',N'Võ Thuật',90 ,N'Trung Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P003',N'Black Adam',N'Hành Động',90 ,N'Hoa Kỳ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P004',N'Bán Đảo',N'Kinh Dị',120 ,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P005',N'Tình Yêu Hay Tiền Tỷ',N'Tâm Lí',120 ,N'Thái Lan');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P006',N'Công Chúa Nhỏ Của Bà',N'Tâm Lí',120 ,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P007',N'Tân Thiện Nữ U Hồn',N'Võ Thuật,Cổ Trang',60,N'Trung Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P008',N'Linh Thú',N'Võ Thuật,Viễn Tưởng',120,N'Trung Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P009',N' Liên Minh Công Lý Bóng Đêm',N'Tâm Lí',90,N'Hoa Kỳ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P010',N' Pháp Sư Mù',N'Tâm Lí',90,N'Việt Nam');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P011',N'Mộc Lan Truyền Kỳ',N'Võ Thuật,Cổ Trang',120,N'Trung Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P012',N'Đại Thảm Họa Núi Baekdu',N'Hành Động', 120 ,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P013',N'Chồng Cũ, Tình Mới',N'Tâm Lí',90,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P014',N'Ôm Hờ Yêu Thật',N'Tâm Lí,Hài Hước',90,N'Thái Lan');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P015',N'Bắc Kim Thang',N'Kinh Dị',90,N'Việt Nam');

-- Nhập Dữ Liệu Vào Bảng Thực Đơn 
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'cocacola',25000,'vnd',N'Nước Uống');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'7Up',25000,'vnd',N'Nước Uống');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Pepsi',25000,'vnd',N'Nước Uống');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Trà ôLong',25000,'vnd',N'Nước Uống');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Fanta',20000,'vnd',N'Nước Uống');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Sprite',20000,'vnd',N'Nước Uống');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Mirinda',15000,'vnd',N'Nước Uống');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Trà Sữa',30000,'vnd',N'Nước Uống');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Bắp Rang Bơ Caramel',35000,'vnd',N'Thức ăn Nhanh');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Bắp Mỹ Rang Bơ ',30000,'vnd',N'Thức Ăn Nhanh');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Bắp Rang Bơ Chôcolate',35000,'vnd',N'Thức Ăn Nhanh');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Bắp Rang Bơ PhoMai',40000,'vnd',N'Thức Ăn Nhanh');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Bắp Rang Bơ vị Ngọt',25000,'vnd',N'Nước Uống');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Bắp Rang Bơ vị Mặn',25000,'vnd',N'Nước Uống');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'Snacks',15000,'vnd',N'Thức Ăn Nhanh');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'My Combo(1 bắp + 1 nước)',85000,'vnd',N'Thức Ăn + Đồ Uống');
Insert Into ThucDon(TenMon,DonGia,DonViTinh,Loai) Values
(N'ComboXL(1 bắp + 2 nước)',105000,'vnd',N'Thức Ăn + Đồ Uống');

-- Nhập Dữ Liệu Vào Bảng Phòng Chiếu 
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC01',100,'200',N'Loại A',N'Loại C',N'Tốt');
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC02',80,'180',N'Loại A',N'Loại B',N'Tốt');
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC03',80,'180',N'Loại C',N'Loại B',N'Tốt');
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC04',70,'180',N'Loại B',N'Loại A',N'Tốt');
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC05',100,'200',N'Loại B',N'Loại C',N'Khá');
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC06',100,'200',N'Loại C',N'Loại A',N'Khá');
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC07',80,'180',N'Loại A',N'Loại A',N'Tốt');
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC08',100,'200',N'Loại B',N'Loại C',N'Khá');
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC09',150,'300',N'Loại A',N'Loại A',N'Tốt');
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC10',80,'180',N'Loại C',N'Loại A',N'Khá');
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC11',150,'300',N'Loại A',N'Loại B',N'Tốt');
Insert into PhongChieu(MaPhong,SoluongGhe,DienTich,MayChieu,AmThanh,TinhTrang) values
(N'PC10',150,'300',N'Loại A',N'Loại C',N'Khá');

--Nhập DữLiệu Vào Bảng Ghế
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC04',N'A2',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC04',N'A6',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC02',N'C3',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC10',N'B6',80000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC06',N'A4',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC08',N'A6',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC07',N'A6',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC03',N'F6',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC02',N'G1&G2',105000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'B3',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC09',N'E4',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'D6',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC02',N'B3',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC07',N'G5&G6',150000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC02',N'F3',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC05',N'A5',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC06',N'E6',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC08',N'C2',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'E5',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC03',N'B4',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC10',N'G3&G4',105000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC09',N'E4',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'G1&G2',105000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC05',N'G5&G6',105000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC03',N'G3&G4',105000);


-- Nhập Dữ Liệu Vào Bảng Hóa Đơn
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV004','2022-05-22');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV005','2022-07-21');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV009','2022-11-18');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV011','2022-04-11');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV015','2022-07-08');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV011','2022-12-04');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV004','2022-03-12');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV004','2022-03-16');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV005','2022-10-22');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV015','2022-09-02');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV009','2022-06-07');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV011','2022-11-02');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV005','2022-12-18');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV004','2022-10-08');
Insert into HoaDon(MaNV,NgayLap) values 
(N'NV009','2022-02-12');

--Nhập Dữ Liệu Vào Bảng Hóa Đơn Chi Tiết
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('2','2','2');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('4','15','1');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('5','17','1');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('12','16','4');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('10','2','2');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('9','11','2');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('15','18','1');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('3','10','3');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('7','12','2');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('14','3','1');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('8','5','2');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('13','8','2');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('6','17','1');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('15','18','1');
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
('16','2','2');


-- Nhập Dữ Liệu Vào Bảng Lịch Chiếu
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P001',16,'2022-07-13','PC09');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P012',10,'2022-01-29','PC11');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P004',9,'2022-11-01','PC08');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P010',17,'2021-12-30','PC03');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P003',8,'2022-09-15','PC10');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P008',15,'2022-02-10','PC07');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P002',18,'2022-08-12','PC01');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P005',20,'2021-01-22','PC02');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P009',13,'2021-11-15','PC05');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P015',19,'2022-05-30','PC04');


--Nhập Dữ Liệu Vào Bảng Vé
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV01','NV001','2022-08-18','A2','PC04','P005');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV02','NV008','2022-08-11','A6','PC04','P015');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV014','NV010','2022-07-07','C3','PC02','P007');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV05','NV014','2022-01-22','A4','PC06','P002');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV06','NV013','2022-06-06','A6','PC08','P011');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV07','NV008','2022-11-12','F6','PC03','P006');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV08','NV001','2022-01-11','G1&G2','PC02','P008');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV09','NV001','2022-12-09','B3','PC01','P003');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV10','NV010','2022-04-30','E4','PC09','P012');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV11','NV013','2022-05-08','D6','PC01','P009');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV12','NV008','2022-09-27','G5&G6','PC05','P004');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV13','NV001','2022-12-01','C2','PC08','P005');
insert into Ve(MaVe,MaNV,NgayLap,MaGhe,MaPhong,MaPhim)values
('MV14','NV010','2022-09-15','G3&G4','PC03','P013');








