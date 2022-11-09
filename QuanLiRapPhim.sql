create database duan
go
use  duan1
go
create table NhanVien(
	MaNV nvarchar(10) , /* Pk */
	HoTen nvarchar(100),
	GioiTinh bit,
	NgaySinh date,
	NgayVaoLam date,
	ChucVu nvarchar(100),
	Luong int,
	Hinh nvarchar(100),
	GhiChu nvarchar(200),
	MatKhau nvarchar(50)
	primary key (MaNV)
	 
)
create table Phim(
	MaPhim nvarchar(10),	/* PK */
	TenPhim nvarchar(200),
	TheLoai nvarchar(100),
	ThoiLuong int,
	QuocGia nvarchar(50),
	Hinh nvarchar(100),
	GhiChu nvarchar(200)
	primary key (MaPhim)

)
create table HoaDon(
	MaHD int IDENTITY(1,1),  /* Pk */
	MaNV nvarchar(10), /* Fk */
	NgayLap date
	primary key (MAHD),
	FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
)
create table ThucDon(
	MaMon nvarchar(10), /* Pk */
	TenMon nvarchar(200), 
	DonGia Float,
	DonViTinh nvarchar(50),
	Loai nvarchar(50),
	Hinh nvarchar(100),
	GhiChu nvarchar(200)
	primary key (MaMon)
	
)
create table PhongChieu(
	MaPhong nvarchar(10), /* Pk */
	SoluongGhe int,
	DienTich nvarchar(50),
	MayChieu nvarchar(50),
	AmThanh nvarchar(50),
	TinhTrang nvarchar(200),
	GhiChu nvarchar(200),
	primary key (MaPhong)
)

create table Ghe(
	MaPhong nvarchar (10), /* Fk  */
	MaGhe nvarchar(10), /*  PK */
	Gia Float,
	primary key (MaGhe,MaPhong),
	FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong)


	
)
create table HoaDonCT(
	MaHD int identity(1,1), /* PK Fk */
	MaMon nvarchar(10), /* PK Fk */
	SoLuong int
	primary key (MaHD,MaMon),
	FOREIGN KEY (MaMon) REFERENCES Thucdon(MaMon),
	FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD)
)

create table LichChieu(
	MaLichChieu nvarchar(20), /* PK */
	MaPhim nvarchar(10),	/* Fk */
	GioChieu int,
	NgayChieu date,
	MaPhong nvarchar(10) /* Fk */
	primary key (MaLichChieu),
	FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim),
	FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong)
)
create table Ve(
	MaVe nvarchar(10), /* Pk */
	MaNV nvarchar(10), /* Fk */
	NgayLap date,
	MaGhe nvarchar(10), /* Fk */
	MaPhong nvarchar(10), /* Fk */
	MaPhim nvarchar(10), /* Fk */
	primary key (MaVe),
	FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),	
	FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong),
	FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim)

)




