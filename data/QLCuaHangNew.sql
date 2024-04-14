use master
go

--drop database QLCuaHang
go
create database QLCuaHang
go
use QLCuaHang

create table KhuyenMai(
	maKhuyenMai INT IDENTITY(1000,1) primary key  NOT NULL,
	ngayBatDau datetime NOT NULL,
	ngayKetThuc datetime NOT NULL,
    giamGia float,
    dieuKien float NOT NULL,
    mucGiamToiDa float
);
create table KhachHang(
	maKhachHang INT IDENTITY(2000,1) PRIMARY KEY NOT NULL,
    ten NVARCHAR(50) NOT NULL,
    soDienThoai NVARCHAR(20) NOT NULL,
    diaChi NVARCHAR(100) NOT NULL,
    loaiKhachHang NVARCHAR(50)
);
create table NhanVien(
	maNhanVien INT IDENTITY(3000,1) primary key NOT NULL,
	ten nvarchar(50) NOT NULL,
	soDienThoai NVARCHAR(20) NOT NULL,
	gioiTinh bit NOT NULL,
	luong float  NOT NULL,
	loai nvarchar(50)
);
create table TaiKhoan(
	maNhanVien int PRIMARY KEY NOT NULL,
    matKhau NVARCHAR(50) NOT NULL,
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);
create table NhaCungCap(
	maNhaCungCap INT IDENTITY(4000,1) PRIMARY KEY NOT NULL,
    tenNhaCungCap NVARCHAR(100) NOT NULL,
    diaChi NVARCHAR(100) NULL
);
create table SanPham(
	maSanPham INT IDENTITY(5000,1) PRIMARY KEY NOT NULL,
    ten NVARCHAR(255) NOT NULL,
    giaSanPham FLOAT NOT NULL,
    donVi NVARCHAR(50) NULL,
	loaiSanPham NVARCHAR(50) NULL
)
create table ChiTietCungCap(
	maSanPham int NOT NULL,
    maNhaCungCap int NOT NULL,
    ngayGiao DATETIME NOT NULL,
    soLuong INT NOT NULL,
    gia FLOAT,
    donVi NVARCHAR(50) NULL,
    PRIMARY KEY (maSanPham, maNhaCungCap),
    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham),
    FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap(maNhaCungCap)
);
create table HoaDon(
	maDon INT IDENTITY(6000,1) PRIMARY KEY NOT NULL,
    maKhachHang int NOT NULL,
    maNhanVien int NOT NULL,
    maKhuyenMai int,
    ngayMua DATETIME NOT NULL,
    tongTien FLOAT NOT NULL,
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien),
    FOREIGN KEY (maKhuyenMai) REFERENCES KhuyenMai(maKhuyenMai)
)
create table ChiTietHoaDon(
	maDon int,
    maSanPham int,
    thanhTien FLOAT,
    soLuong INT,
    PRIMARY KEY (maDon, maSanPham),
    FOREIGN KEY (maDon) REFERENCES HoaDon(maDon),
    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)
);

-- Dữ liệu cho bảng KhuyenMai
INSERT INTO KhuyenMai (ngayBatDau, ngayKetThuc, giamGia, dieuKien, mucGiamToiDa)
VALUES
('2024-04-01', '2024-04-30', 0.1, 500000, 200000),
('2024-05-01', '2024-05-31', 0.15, 700000, 250000),
('2024-07-01', '2024-07-31', 0.11, 510000, 205000),
('2024-08-01', '2024-08-30', 0.12, 520000, 210000),
('2024-09-01', '2024-09-05', 0.13, 530000, 215000),
('2024-10-01', '2024-10-11', 0.14, 540000, 220000),
('2024-11-01', '2024-11-26', 0.15, 550000, 225000),
('2024-12-01', '2024-12-21', 0.16, 560000, 230000),
('2025-01-01', '2023-01-15', 0.17, 570000, 235000),
('2025-02-01', '2023-02-28', 0.18, 580000, 240000),
('2025-03-01', '2024-03-12', 0.19, 590000, 245000),
('2025-04-01', '2025-04-17', 0.20, 600000, 250000),
('2025-05-01', '2025-05-22', 0.21, 610000, 255000),
('2025-06-01', '2025-06-01', 0.22, 620000, 260000),
('2025-07-01', '2025-07-09', 0.23, 630000, 265000),
('2025-08-01', '2025-08-29', 0.24, 640000, 270000),
('2025-09-01', '2025-09-13', 0.25, 650000, 275000),
('2025-10-01', '2025-10-05', 0.26, 660000, 280000),
('2025-11-01', '2025-11-24', 0.27, 670000, 285000),
('2025-12-01', '2025-12-19', 0.28, 680000, 290000);

-- Dữ liệu cho bảng KhachHang
INSERT INTO KhachHang (ten, soDienThoai, diaChi, loaiKhachHang)
VALUES
(N'Nguyễn Phúc Vinh', '0123456789', N'123 Đường Cộng Hòa, Quận Tân Bình, TP. HCM', N'VIP'),
(N'Trần Văn Phi', '0987654321', N'456 Đường Nguyễn Văn, Quận Bình Thạnh, TP. HCM', N'Thường'),
(N'Nguyễn Văn Tùng', '0123456789', N'123 Đường Nguyễn Trãi, Quận 1, TP. HCM', N'VIP'),
(N'Nguyễn Thị Thanh Thảo', '0987654321', N'456 Đường Nguyễn Kiệm, Quận Phú Nhuận, TP. HCM', NULL),
(N'Trần Văn Phi Hoàng', '0123456789', N'789 Đường Nguyễn Hữu Cảnh, Quận Bình Thạnh, TP. HCM', NULL),
(N'Trần Thị Lê Huyền', '0987654321', N'101 Đường Lê Lợi, Quận Tân Phú, TP. HCM', N'VIP'),
(N'Lê Văn Tuyết', '0123456789', N'456 Đường Lê Lai, Quận 7, TP. HCM', NULL),
(N'Phạm Thị Hạnh', '0987654321', N'789 Đường Điện Biên Phủ, Quận Bình Thạnh, TP. HCM', N'VIP'),
(N'Lê Văn Nam', '0123456789', N'123 Đường Võ Văn Ngân, Quận Thủ Đức, TP. HCM', N'VIP'),
(N'Phạm Thị Lan', '0987654321', N'456 Đường Kha Vạn Cân, Quận Thủ Đức, TP. HCM', NULL),
(N'Võ Văn Long', '0123456789', N'789 Đường Phạm Văn Chí, Quận 6, TP. HCM', NULL),
(N'Đường Thị Hiền', '0987654321', N'101 Đường Nguyễn Đình Chiểu, Quận 3, TP. HCM', N'VIP'),
(N'Trần Văn Đông', '0123456789', N'456 Đường Hai Bà Trưng, Quận 1, TP. HCM', NULL),
(N'Nguyễn Thị Bích', '0987654321', N'789 Đường Nam Kỳ Khởi Nghĩa, Quận 3, TP. HCM', N'VIP'),
(N'Lê Văn Tường', '0123456789', N'123 Đường Cách Mạng Tháng Tám, Quận 3, TP. HCM', NULL),
(N'Phạm Thị Hồng', '0987654321', N'456 Đường Lý Thái Tổ, Quận 1, TP. HCM', N'VIP'),
(N'Võ Văn Hồng', '0123456789', N'789 Đường Nguyễn Thị Minh Khai, Quận 1, TP. HCM', NULL),
(N'Đường Thị Nga', '0987654321', N'101 Đường Pasteur, Quận 1, TP. HCM', N'VIP'),
(N'Trần Văn Bình', '0123456789', N'456 Đường Tôn Đức Thắng, Quận 1, TP. HCM', NULL),
(N'Nguyễn Thị Mai', '0987654321', N'789 Đường Võ Văn Tần, Quận 3, TP. HCM', N'VIP'),
(N'Lê Văn Hùng', '0123456789', N'123 Đường Nguyễn Cửu Trinh, Quận 1, TP. HCM', NULL),
(N'Phạm Thị Dung', '0987654321', N'456 Đường Nguyễn Du, Quận 1, TP. HCM', N'VIP'),
(N'Võ Văn Phong', '0123456789', N'789 Đường Lê Thị Riêng, Quận 1, TP. HCM', NULL),
(N'Đường Thị Thanh', '0987654321', N'101 Đường Phạm Ngũ Lão, Quận 1, TP. HCM', N'VIP'),
(N'Trần Văn Kiên', '0123456789', N'456 Đường Bùi Viện, Quận 1, TP. HCM', NULL),
(N'Nguyễn Thị Huyền', '0987654321', N'789 Đường Đề Thám, Quận 1, TP. HCM', N'VIP'),
(N'Lê Văn Quân', '0123456789', N'123 Đường Nguyễn Đình Chiểu, Quận 3, TP. HCM', NULL),
(N'Phạm Thị Loan', '0987654321', N'456 Đường Trần Hưng Đạo, Quận 1, TP. HCM', N'VIP'),
(N'Lê Thị Huệ', '0987654321', N'789 Đường Nguyễn Trãi, Quận 1, TP. HCM', N'VIP'),
(N'Phạm Văn Tùng', '0123456789', N'123 Đường Lê Văn Quới, Quận Bình Tân, TP. HCM', NULL),
(N'Võ Thị Lan', '0987654321', N'456 Đường Lê Trọng Tấn, Quận Tân Phú, TP. HCM', NULL),
(N'Đường Văn Long', '0123456789', N'789 Đường Nguyễn Cửu Trinh, Quận 1, TP. HCM', N'VIP'),
(N'Trần Thị Hồng', '0987654321', N'101 Đường Võ Văn Ngân, Quận Thủ Đức, TP. HCM', NULL),
(N'Nguyễn Văn Đông', '0123456789', N'456 Đường Kha Vạn Cân, Quận Thủ Đức, TP. HCM', N'VIP'),
(N'Lê Thị Bích', '0987654321', N'789 Đường Phạm Văn Chí, Quận 6, TP. HCM', NULL),
(N'Phạm Văn Hồng', '0123456789', N'123 Đường Nguyễn Đình Chiểu, Quận 3, TP. HCM', N'VIP'),
(N'Võ Thị Nga', '0987654321', N'456 Đường Hai Bà Trưng, Quận 1, TP. HCM', NULL),
(N'Đường Văn Bình', '0123456789', N'789 Đường Nam Kỳ Khởi Nghĩa, Quận 3, TP. HCM', N'VIP'),
(N'Trần Thị Mai', '0987654321', N'101 Đường Cách Mạng Tháng Tám, Quận 3, TP. HCM', NULL);
UPDATE KhachHang
SET loaiKhachHang = N'Thường'
WHERE loaiKhachHang IS NULL;
-- Dữ liệu cho bảng NhanVien
INSERT INTO NhanVien (ten, soDienThoai, gioiTinh, luong, loai)
VALUES
(N'Nguyễn Văn Nam', '0123456789', 1, 15000000, N'Quản lý'),
(N'Trần Thị Hương', '0987654321', 0, 12000000, N'Nhân viên'),
(N'Bùi Thiên Hoàng', '0935456789', 1, 15000000, N'Nhân viên');

-- Dữ liệu cho bảng TaiKhoan
INSERT INTO TaiKhoan (maNhanVien,matKhau)
VALUES
(3000,'password1'),
(3001,'password2'),
(3002,'password3');

-- Dữ liệu cho bảng NhaCungCap
INSERT INTO NhaCungCap (tenNhaCungCap, diaChi)
VALUES
(N'Công ty ITeam', N'789 Đường Phan Văn Trị, Quận Gò Vấp, TP. HCM'),
(N'Công ty TNHH Chánh Thắng', N'101 Đường Quang Trung, Quận Gò Vấp, TP. HCM'),
(N'Công ty FPT Shop', N'202 Pasteur, Phường 6, Quận 3, TP. HCM'),
(N'Công ty Điện máy xanh', N'123 Nguyễn Trãi, Phường Nguyễn Cư Trinh, Quận 1, TP. HCM'),
(N'Công ty VinGroup', N'777 Đại lộ Thăng Long, Phường Tân Hưng, Quận 7, TP. HCM'),
(N'Công ty TH True Milk', N'123 Nguyễn Duy Trinh, Phường Bình Trưng Tây, Quận 2, TP. HCM'),
(N'Công ty Masan Consumer', N'345 Lê Thị Riêng, Phường Thới An, Quận 12, TP. HCM'),
(N'Công ty Unilever Việt Nam', N'101 Võ Văn Ngân, Phường Linh Chiểu, Quận Thủ Đức, TP. HCM'),
(N'Công ty Coca-Cola Việt Nam', N'789 Nguyễn Tất Thành, Phường 13, Quận 4, TP. HCM');

-- Dữ liệu cho bảng SanPham
INSERT INTO SanPham (ten, giaSanPham, donVi, loaiSanPham)
VALUES
(N'Bánh mì', 10000, N'Cái', N'Thực phẩm'),
(N'Nước ngọt', 15000, N'Chai', N'Đồ uống'),
(N'Sữa đậu nành', 20000, N'Lít', N'Thực phẩm'),
(N'Mì gói', 8000, N'Gói', N'Thực phẩm'),
(N'Chả lụa', 30000, N'Cái', N'Thực phẩm'),
(N'Bia Heineken', 20000, N'Lon', N'Đồ uống'),
(N'Bia Tiger', 18000, N'Lon', N'Đồ uống'),
(N'Thịt gà', 120000, N'Kg', N'Thực phẩm'),
(N'Rau cải', 25000, N'Kg', N'Thực phẩm'),
(N'Chả cá', 35000, N'Cái', N'Thực phẩm'),
(N'Sữa chua', 10000, N'Chai', N'Thực phẩm'),
(N'Bột ngọt AJI-NO-MOTO', 10000, N'Gói', N'Thực phẩm'),
(N'Nước mắm', 15000, N'Chai', N'Thực phẩm'),
(N'Nước tương', 15000, N'Chai', N'Thực phẩm'),
(N'Bia Sài Gòn', 17000, N'Lon', N'Đồ uống'),
(N'Thịt bò', 220000, N'Kg', N'Thực phẩm'),
(N'Chanh', 5000, N'Kg', N'Thực phẩm'),
(N'Dầu ăn', 25000, N'Chai', N'Thực phẩm'),
(N'Mì tôm', 5000, N'Gói', N'Thực phẩm');

-- Dữ liệu cho bảng HoaDon
INSERT INTO HoaDon (maKhachHang, maNhanVien, maKhuyenMai, ngayMua, tongTien)
VALUES
(2000, 3000, 1000, '2024-04-15', 200000),
(2001, 3001, NULL, '2024-04-20', 300000),
(2002, 3002, 1001, '2024-04-25', 400000),
(2003, 3000, NULL, '2024-04-30', 500000),
(2000, 3001, 1000, '2024-05-05', 350000),
(2001, 3002, NULL, '2024-05-10', 250000),
(2002, 3000, 1001, '2024-05-15', 450000),
(2003, 3001, NULL, '2024-05-20', 150000),
(2000, 3002, 1000, '2024-05-25', 200000);
-- Dữ liệu cho bảng ChiTietHoaDon
INSERT INTO ChiTietHoaDon (maDon, maSanPham, thanhTien, soLuong)
VALUES
(6001, 5000, 90000, 10),
(6001, 5001, 110000, 5),
(6002, 5001, 150000, 10),
(6003, 5000, 180000, 20),
(6003, 5002, 120000, 6),
(6004, 5001, 150000, 10),
(6004, 5003, 250000, 10),
(6005, 5002, 108000, 6),
(6005, 5003, 120000, 4),
(6006, 5001, 150000, 10),
(6007, 5000, 90000, 10),
(6007, 5007, 18000, 12);


-- Dữ liệu cho bảng ChiTietCungCap
INSERT INTO ChiTietCungCap (maSanPham, maNhaCungCap, ngayGiao, soLuong, gia, donVi)
VALUES
(5000, 4000, '2024-04-05', 100, 9000, N'Cái'),
(5001, 4001, '2024-04-10', 200, 12000, N'Chai'),
(5002, 4002, '2024-04-12', 50, 18000, N'Lít'),
(5003, 4003, '2024-04-15', 100, 28000, N'Gói'),
(5004, 4004, '2024-04-18', 200, 4000, N'Cái'),
(5005, 4005, '2024-04-20', 100, 30000, N'Lon'),
(5006, 4006, '2024-04-22', 50, 13000, N'Lon'),
(5007, 4007, '2024-04-25', 150, 18000, N'Kg'),
(5008, 4008, '2024-04-27', 100, 25000, N'Kg'),
(5009, 4000, '2024-04-05', 100, 9000, N'Cái'),
(5010, 4001, '2024-04-10', 200, 12000, N'Chai'),
(5011, 4002, '2024-04-12', 50, 18000, N'Gói'),
(5012, 4003, '2024-04-15', 100, 28000, N'Chai'),
(5013, 4004, '2024-04-18', 200, 4000, N'Chai'),
(5014, 4005, '2024-04-20', 100, 30000, N'Lon'),
(5015, 4006, '2024-04-22', 50, 13000, N'Kg'),
(5016, 4007, '2024-04-25', 150, 18000, N'Kg'),
(5017, 4008, '2024-04-27', 100, 25000, N'Chai');
