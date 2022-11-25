create database Duan1_QLRP
go
use  Duan1_QLRP
go

--ham tao khoa chinh tu dong theo mau
CREATE FUNCTION AUTO_IDUS()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MaUser) FROM Users) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaUser, 3)) FROM Users
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'US00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'US0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END

CREATE FUNCTION AUTO_IDMV()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MaVe) FROM Ve) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaVe, 3)) FROM Ve
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'MV00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'MV0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END

create trigger tg_xoaPC on phongchieu instead of delete
as
begin
	delete from ve where MaPhong in (
		select MaPhong from deleted
	)
	delete from LichChieu where MaPhong in (
		select MaPhong from deleted
	)
	delete from Ghe where MaPhong in (
		select MaPhong from deleted
	)
	delete from PhongChieu where MaPhong in (
		select MaPhong from deleted
	)
end


create table Role(
	MaRole int primary key,
	VaiTro nvarchar(100)

)

--bang nhan vien
create table Users(
	MaUser nvarchar(10) primary key constraint idnv default dbo.AUTO_IDUS(), /* Pk */
	HoTen nvarchar(100) not null,
	SoDT nvarchar(10),
	Email nvarchar(30),
	GioiTinh bit not null default 1,
	NgaySinh date not null,
	NgayVaoLam date not null DEFAULT getdate(),
	MaRole int not null,
	Luong int null check (luong>0),
	Hinh nvarchar(100),
	GhiChu nvarchar(200),
	MatKhau nvarchar(50)not null
	constraint fk_rl_us FOREIGN KEY (MaRole) REFERENCES Role(MaRole) on update cascade
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
	MaUser nvarchar(10) not null, /* Fk */
	NgayLap date not null DEFAULT getdate(),
	constraint fk_hd_us FOREIGN KEY (MaUser) REFERENCES Users(MaUser) on update cascade
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
	MaVe nvarchar(10) primary key not null constraint idmv default dbo.AUTO_IDMV(), /* Pk */
	MaUser nvarchar(10), /* Fk */
	NgayLap date DEFAULT getdate(),
	MaGhe nvarchar(10) not null, /* Fk */
	MaPhong nvarchar(10) not null, /* Fk */
	MaLichChieu int not null, /* Fk */
	constraint fk_ve_nv FOREIGN KEY (MaUser) REFERENCES Users(MaUser) on update cascade,	
	constraint fk_ve_gh FOREIGN KEY (MaGhe, MaPhong) REFERENCES Ghe(MaGhe, MaPhong) on update cascade,
	constraint fk_ve_lc FOREIGN KEY (MaLichChieu) REFERENCES LichChieu(MaLichChieu) on update cascade
)
-- Nhập dữ liệu bảng role
insert into Role(MaRole, VaiTro) values
(1,N'Quản lý rạp');
insert into Role(MaRole, VaiTro) values
(2,N'Nhân viên bán hàng');
insert into Role(MaRole, VaiTro) values
(3,N'Nhân viên đặt vé');
insert into Role(MaRole, VaiTro) values
(4,N'Nhân viên quản lý phim');
insert into Role(MaRole, VaiTro) values
(5,N'Nhân viên soát vé');
insert into Role(MaRole, VaiTro) values
(6,N'Khách hàng');

-- Nhập Dữ Liệu Bảng Nhân Viên
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Hồ Văn Khánh','0123987654','mailam114@gmail.com',1,'2003-10-22','2022-05-23',3,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Đồng Quốc Thành','0987654321','tailanh766@gmail.com',1,'2003-05-12','2021-11-12',1,6000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Đặng Quang Huy','0768915158','danghuy1123@gmail.com',1,'2003-09-18','2022-07-22',4,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Trịnh Thế Qúy','0998877766','huydq112@gmail.com',1,'2003-02-13','2022-01-08',2 ,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Phan Diệu Linh','0123645798','tutu222@gmail.com',0,'2003-12-24','2022-03-02',2,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Trần Văn Hiệp','0331278644','hiepvan776@gmail.com',1,'2003-09-21','2022-05-23',5,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Phạm Ngọc Sang','1237650489','sangsang88@gmail.com',1,'2003-04-15','2022-03-17',5,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Phạm Thị Mỹ Lệ','095643218','myle193@gmail.com',0,'2003-10-22','2022-03-03',3,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Nguyễn Thị Hồng','8765912340','hongng22@gmail.com',0,'2002-08-17','2022-09-12',2,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Nguyễn Văn Huy','0674312895','huydq112@gmail.com',1,'2003-03-22','2022-05-23',3,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Phạm Thị Ái Hoanh','7658893123','aihoanh229@gmail.com',0,'2002-11-12','2022-08-26',2,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Lê Minh Trọng','0934069967','trongtt334@gmail.com',1,'2003-09-26','2022-05-06',4,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Nguyễn Thị Kiều Diễm','0765889123','diemkieu287@gmail.com',0,'2003-12-04','2022-10-19',5,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Hoàng Thị Diễm Phương','0995642231','phuongdm667@gmail.com',0,'2001-02-02','2022-10-22',5,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,Luong,MatKhau) values 
(N'Trần Đức Tiến','0332341143','tienduc098@gmail.com',1,'2003-09-18','2022-09-12',2,3000000,'0000');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,MatKhau) values 
(N'Nguyễn Thị Kim Anh','1237896450','kimanh112@gmail.com',0,'1968-12-01','',6,'1111');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,MatKhau) values 
(N'Đặng Tấn Tài','7755462087','taidang096@gmail.com',1,'1968-11-01','',6,'1111');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,MatKhau) values 
(N'Mã Tiến An','4321678095','antienma23@gmail.com',1,'1998-10-11','',6,'1111');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,MatKhau) values 
(N'Trần Văn Đại','1789450323','daitran665@gmail.com',1,'1997-04-01','',6,'1111');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,MatKhau) values 
(N'Mason Gỗ Xanh','1900567782','xanhmason760@gmail.com',0,'1999-09-13','',6,'1111');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,MatKhau) values 
(N'Quý Quét Nơ','0180033889','nono886@gmail.com',1,'1993-12-01','',6,'1111');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,MatKhau) values 
(N'Đặng Mắc Hài','2094317812','danghai774@gmail.com',1,'2001-11-17','',6,'1111');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,MatKhau) values 
(N'Ra Phen Va Ren','5578901234','renva246@gmail.com',0,'2003-12-17','',6,'1111');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,MatKhau) values 
(N'Cao Ri Đỗ','6572218903','dori434@gmail.com',1,'2003-11-16','',6,'1111');
Insert into Users(HoTen,SoDT, Email, GioiTinh,NgaySinh,NgayVaoLam,MaRole,MatKhau) values 
(N'Phan Sa Tị','9978065541','tisa2323@gmail.com',1,'2004-06-06','',6,'1111');


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
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P016',N'Cô Gái Từ Qúa Khứ',N'Hành Đọng',90,N'Việt Nam');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P017',N'Đặc Vụ Xuyên Quốc Gia',N'Hành Động,Hài',120,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P018',N'Nghi Thức Cấm',N'Kinh Dị',120,N'Thái Lan');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P019',N'Khỉ Con Lon Ton Thế Gioi',N'Hoạt Hình',90,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P020',N'Thế Thân',N'Kinh Dị',60,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P021',N'Yêu Không Sợ Hãi',N'Tình Cảm,Hài',120,N'Thái Lan');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P022',N'HARRY POTTER VÀ HOÀNG TỬ LAI',N'Phiêu Lưu, Thần Thoại',120,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P023',N'Thực Đơn Bí Ẩn',N'Kinh Dị',90,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P024',N'BLACK PANTHER: WAKANDA BẤT DIỆT',N'Hành Động',150,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P025',N'LYLE: CHÚ CÁ SẤU BIẾT HÁT',N'Hoạt Hình,Hài',120,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P026',N'Doraemon: Nobita Và Cuộc Chiến Vũ Trụ Tí Hon 2021',N'Hoạt Hình,Hài',120,N'Nhật Bản');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P027',N'Hố Đen Tử Thần',N'Hành Động, Viễn Tưởng',150,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P028',N'Phi Công Siêu Đẳng Maverick',N'Hành Động,Phiêu Lưu',130,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P029',N'Điều Ước Cuối Của Tù Nhân 2037',N'Tâm Lí, Tình Cảm',126,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P030',N' Hậu Duệ Tình Người Duyên Ma',N'Kinh Dị,Hài',100,N'Thái Lan');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P031',N'Vết Nứt: Ám Hồn Trong Tranh',N'Kinh Dị',100,N'Thái Lan');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P032',N'Dòng Máu Đặc Cảnh',N'Tâm Lí , Hình Sự',120,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P033',N'Gánh Nặng Ngàn Cân Của Tài Năng Kiệt Xuất',N'Hành Động,Hài',130,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P034',N'Minions: Sự Trỗi Dậy Của Gru',N'Hoạt Hình,Hài',90,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P035',N'Ngoài Vòng Pháp Luật 2',N'Hành Động,Tội Phạm',110,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P036',N'Bản Tin Chết',N'Hình Sự',110,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P037',N'Ngôi Đền Kỳ Quái 3',N'Kinh Dị,Hài',110,N'Thái Lan');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P038',N'Sát Thủ Nhân Tạo 2: Mẫu Vật Còn Lại',N'Hành Động, Bỉ Ẩn',140,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P040',N'Cảnh Sát Vũ Trụ',N'Hoạt Hình , Viễn Tưởng',110,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P041',N'Điện Thoại Đen',N'Kinh Dị',100,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P042',N'Người Môi Giới',N'Tâm Lí',130,N'Hàn Quốc');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P043',N'Tăng Tốc... Về Phía Em',N'Tình Cả,Hài',120,N'Thái Lan');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P044',N'Thế Giới Khủng Long: Lãnh Địa	',N'Hành Động,Phiêu Lưu',150,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P045',N'Mồi Cá Mập',N'Gay Cấn , Kinh Dị',100,N'Anh');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P046',N'Người Khởi Lửa',N'Hành Động',120,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P047',N'Cuộc Chiến Đa Vũ Trụ',N'Hành Động',120,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P048',N'Ma Cà Rồng',N'Hành Động',120,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P049',N'Nhím Sonic 2',N'Hành Động',150,N'Mỹ');
Insert into Phim(MaPhim,TenPhim,TheLoai,ThoiLuong,QuocGia) values
('P050',N'Batman: Vạch Trần Sự Thật',N'Hành Động',130,N'Mỹ');



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
(N'PC12',150,'300',N'Loại A',N'Loại C',N'Khá');

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
(N'PC11',N'E5',75000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'G1&G2',105000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC05',N'G5&G6',105000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC03',N'G3&G4',105000);

Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'A1',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'A2',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'A6',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'A3',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'A5',105000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'A4',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'B1',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'B2',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'B6',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'B4',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'B5',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'C1',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'C2',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'C3',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'C4',105000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'C5',105000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'C6',105000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'D1',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'D2',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'D3',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'D4',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'D5',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'E1',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'E2',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'E3',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'E4',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'E6',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'F1',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'F2',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'F3',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'F4',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'F5',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'F6',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'G3&G4',85000);
Insert into Ghe(MaPhong,MaGhe,Gia) values 
(N'PC01',N'G5&G6',85000);

-- Nhập Dữ Liệu Vào Bảng Hóa Đơn
Insert into HoaDon(MaUser,NgayLap) values 
(N'US013','2022-05-22');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US008','2022-07-21');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US006','2022-11-18');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US003','2022-04-11');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US004','2022-07-08');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US012','2022-12-04');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US007','2022-03-12');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US009','2022-03-16');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US010','2022-10-22');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US011','2022-09-02');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US013','2022-06-07');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US014','2022-11-02');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US015','2022-12-18');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US002','2022-10-08');
Insert into HoaDon(MaUser,NgayLap) values 
(N'US001','2022-02-12');

--Nhập Dữ Liệu Vào Bảng Hóa Đơn Chi Tiết
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(1,2,2);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(2,15,1);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(7,17,1);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(8,16,4);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(11,2,2);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(12,11,2);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(14,17,1);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(12,10,3);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(12,12,2);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(3,3,1);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(6,5,2);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(9,8,2);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(2,1,1);
Insert into HoaDonCT(MaHD,MaMon,SoLuong) values
(6,2,2);


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

delete from ve
--Nhập Dữ Liệu Vào Bảng Vé
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US001','2021-08-18','A2','PC04',1);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US008','2021-08-11','A6','PC04',3);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-07-07','C3','PC02',5);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US001','2021-01-22','A4','PC06',2);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US001','2021-06-06','A6','PC08',5);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US001','2021-11-12','F6','PC03',7);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US008','2021-01-11','G1&G2','PC02',10);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US008','2021-12-09','B3','PC01',1);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US008','2021-04-30','E4','PC09',1);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-05-08','D6','PC01',2);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-09-27','G5&G6','PC05',3);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-12-01','C2','PC08',3);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-09-15','G3&G4','PC03',3);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-09-27','G5&G6','PC05',4);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-01-01','C2','PC08',8);
insert into Ve(NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-09-15','G3&G4','PC03',9);

-- Nhập Dữ Liệu Vào Bảng Lịch Chiếu
delete from lichchieu
-- Ngày 1-11-2022
-- Phòng 1 
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P001',8,'2022-11-01','PC01');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P002',10,'2022-11-01','PC01');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P003',13,'2022-11-01','PC01');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P004',16,'2022-11-01','PC01');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P005',20,'2022-11-01','PC01');
--Phòng 2
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P006',8,'2022-11-01','PC02');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P007',10,'2022-11-01','PC02');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P008',13,'2022-11-01','PC02');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P009',16,'2022-11-01','PC02');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P010',20,'2022-11-01','PC02');
--Phòng 3 
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P011',8,'2022-11-01','PC03');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P012',10,'2022-11-01','PC03');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P013',13,'2022-11-01','PC03');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P014',16,'2022-11-01','PC03');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P015',20,'2022-11-01','PC03');
--Phòng 4 
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P016',8,'2022-11-01','PC04');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P017',10,'2022-11-01','PC04');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P018',13,'2022-11-01','PC04');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P019',16,'2022-11-01','PC04');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P020',20,'2022-11-01','PC04');
 
 -- Ngày 2-11-2022

 -- Phòng 5 
 insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P021',8,'2022-11-02','PC05');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P006',10,'2022-11-02','PC05');-- Phim Cũ chiếu lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P011',13,'2022-11-02','PC05'); -- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P022',16,'2022-11-02','PC05');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P009',20,'2022-11-02','PC05');-- Phim Cũ Chiếu Lại
-- Phòng 6 
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P017',8,'2022-11-02','PC06');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P023',10,'2022-11-02','PC06');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P015',13,'2022-11-02','PC06');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P001',16,'2022-11-02','PC06');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P024',20,'2022-11-02','PC06');
-- Phòng 7
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P003',8,'2022-11-02','PC07');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P012',10,'2022-11-02','PC07');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P010',13,'2022-11-02','PC07');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P025',16,'2022-11-02','PC07');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P026',20,'2022-11-02','PC07');
-- Phòng 8 
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P004',8,'2022-11-02','PC08');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P027',10,'2022-11-02','PC08');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P008',13,'2022-11-02','PC08');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P028',16,'2022-11-02','PC08');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P007',20,'2022-11-02','PC08');-- Phim Cũ Chiếu Lại

-- Ngày 3-11-2022 
-- Phòng 9 
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P002',8,'2022-11-03','PC09');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P005',10,'2022-11-03','PC09');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P028',13,'2022-11-03','PC09');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P013',16,'2022-11-03','PC09');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P029',20,'2022-11-03','PC09');

-- Phòng 10 
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P014',8,'2022-11-03','PC10');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P030',10,'2022-11-03','PC10');
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P016',13,'2022-11-03','PC10');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P018',16,'2022-11-03','PC10');-- Phim Cũ Chiếu Lại
insert into LichChieu(MaPhim,GioChieu,NgayChieu,MaPhong)values
('P031',20,'2022-11-03','PC10');


select * from LichChieu
--Nhập Dữ Liệu Vào Bảng Vé 
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US001','2022-10-30','B3','PC01',1);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US008','2022-10-30','A6','PC01',3);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-07-07','C3','PC02',5);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US001','2021-01-22','A4','PC06',2);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US001','2021-06-06','A6','PC08',5);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US001','2021-11-12','F6','PC03',7);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US008','2021-01-11','G1&G2','PC02',10);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US008','2021-12-09','B3','PC01',1);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US008','2021-04-30','E4','PC09',1);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-05-08','D6','PC01',2);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-09-27','G5&G6','PC05',3);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-12-01','C2','PC08',3);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-09-15','G3&G4','PC03',3);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-09-27','G5&G6','PC05',4);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-01-01','C2','PC08',8);
insert into Ve(MaUser,NgayLap,MaGhe,MaPhong,MaLichChieu)values
('US010','2021-09-15','G3&G4','PC03',9);







