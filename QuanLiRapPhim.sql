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
	MaHD int identity(1,1) not null, /* PK Fk */
	MaMon int not null, /* PK Fk */
	SoLuong int not null
	constraint pk_hdct primary key (MaHD,MaMon),
	constraint fk_hdct_td FOREIGN KEY (MaMon) REFERENCES Thucdon(MaMon),
	constraint fk_hdct_hd FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
	check(Soluong > 0)
)

--bang lich chieu
create table LichChieu(
	MaLichChieu nvarchar(20) primary key not null, /* PK */
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


