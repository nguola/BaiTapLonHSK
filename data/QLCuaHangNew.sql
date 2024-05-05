use master
go
drop database QLCuaHang
go
create database QLCuaHang
go
use QLCuaHang

go
create table KhuyenMai(
	maKhuyenMai INT IDENTITY(1000,1) primary key  NOT NULL,
	ngayBatDau datetime NOT NULL,
	ngayKetThuc datetime NOT NULL,
    giamGia float,
    dieuKien NVARCHAR(100) NOT NULL,
);
go
create table KhachHang(
	maKhachHang INT IDENTITY(2000,1) PRIMARY KEY NOT NULL,
    ten NVARCHAR(50) NOT NULL,
    soDienThoai NVARCHAR(20) NOT NULL,
    diaChi NVARCHAR(100) NOT NULL,
    loaiKhachHang NVARCHAR(50)
);
go
create table NhanVien(
	maNhanVien INT IDENTITY(3000,1) primary key NOT NULL,
	ten nvarchar(50) NOT NULL,
	soDienThoai NVARCHAR(20) NOT NULL,
	gioiTinh bit NOT NULL,
	luong float  NOT NULL,
	loai nvarchar(50)
);
go
create table TaiKhoan(
	maNhanVien int PRIMARY KEY NOT NULL,
    matKhau NVARCHAR(50) NOT NULL,
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);
go
create table KhuVuc(
	maKhuVuc INT IDENTITY(4000,1) PRIMARY KEY NOT NULL,
    tenKhuVuc NVARCHAR(100) NOT NULL
);
go
create table SanPham(
	maSanPham INT IDENTITY(5000,1) PRIMARY KEY NOT NULL,
	maKhuyenMai int NOT NULL,
	maKhuVuc int not null,
    ten NVARCHAR(255) NOT NULL,
    giaSanPham FLOAT NOT NULL,
    donVi NVARCHAR(50) NULL,
	loaiSanPham NVARCHAR(50) NULL,
	soLuongTonKho int not null,
	 FOREIGN KEY (maKhuyenMai) REFERENCES KhuyenMai(maKhuyenMai),
    FOREIGN KEY (maKhuVuc) REFERENCES KhuVuc(maKhuVuc),
)
go
create table HoaDon(
	maDon INT IDENTITY(6000,1) PRIMARY KEY NOT NULL,
    maKhachHang int,
    maNhanVien int NOT NULL,
    ngayMua DATETIME NOT NULL,
    tongTien FLOAT NOT NULL,
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien),
)
go
create table ChiTietHoaDon(
	maDon int,
    maSanPham int,
    thanhTien FLOAT,
    soLuong INT,
    PRIMARY KEY (maDon, maSanPham),
    FOREIGN KEY (maDon) REFERENCES HoaDon(maDon),
    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)
);
go
create table PhieuDat(
	maPhieu INT IDENTITY(7000,1) PRIMARY KEY NOT NULL,
	maNhanVien int not null,
	nhaCungCap nvarchar(100) not null,
	ngayDat Datetime not null,
	tongTien float not null
	 FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien),
);
go
create table ChiTietPhieuDat(
	maPhieu int,
    maSanPham int,
    thanhTien float,
	soLuong int,
    PRIMARY KEY (maPhieu, maSanPham),
	FOREIGN KEY (maPhieu) REFERENCES PhieuDat(maPhieu),
    FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)
);
go
-- Dữ liệu cho bảng KhachHang
INSERT INTO KhachHang (ten, soDienThoai, diaChi, loaiKhachHang)
VALUES
(N'Nguyễn Phúc Vinh', '0123456789', N'123 Đường Cộng Hòa, Quận Tân Bình, TP. HCM', N'VIP'),
(N'Trần Văn Phi', '0987654321', N'456 Đường Nguyễn Văn, Quận Bình Thạnh, TP. HCM', N'Thường'),
(N'Nguyễn Văn Tùng', '0123456789', N'123 Đường Nguyễn Trãi, Quận 1, TP. HCM', N'VIP'),
(N'Trần Thị Lê Huyền', '0987654321', N'101 Đường Lê Lợi, Quận Tân Phú, TP. HCM', N'Thường'),
(N'Phạm Thị Hạnh', '0987654321', N'789 Đường Điện Biên Phủ, Quận Bình Thạnh, TP. HCM', N'VIP'),
(N'Lê Văn Nam', '0123456789', N'123 Đường Võ Văn Ngân, Quận Thủ Đức, TP. HCM', N'Thường'),
(N'Đường Thị Hiền', '0987654321', N'101 Đường Nguyễn Đình Chiểu, Quận 3, TP. HCM', N'VIP'),
(N'Nguyễn Thị Bích', '0987654321', N'789 Đường Nam Kỳ Khởi Nghĩa, Quận 3, TP. HCM', N'Thường'),
(N'Phạm Thị Hồng', '0987654321', N'456 Đường Lý Thái Tổ, Quận 1, TP. HCM', N'VIP'),
(N'Đường Thị Nga', '0987654321', N'101 Đường Pasteur, Quận 1, TP. HCM', N'Thường'),
(N'Nguyễn Thị Mai', '0987654321', N'789 Đường Võ Văn Tần, Quận 3, TP. HCM', N'VIP'),
(N'Phạm Thị Dung', '0987654321', N'456 Đường Nguyễn Du, Quận 1, TP. HCM', N'Thường'),
(N'Đường Thị Thanh', '0987654321', N'101 Đường Phạm Ngũ Lão, Quận 1, TP. HCM', N'VIP'),
(N'Nguyễn Thị Huyền', '0987654321', N'789 Đường Đề Thám, Quận 1, TP. HCM', N'Thường'),
(N'Phạm Thị Loan', '0987654321', N'456 Đường Trần Hưng Đạo, Quận 1, TP. HCM', N'VIP'),
(N'Lê Thị Huệ', '0987654321', N'789 Đường Nguyễn Trãi, Quận 1, TP. HCM', N'Thường'),
(N'Đường Văn Long', '0123456789', N'789 Đường Nguyễn Cửu Trinh, Quận 1, TP. HCM', N'VIP'),
(N'Nguyễn Văn Đông', '0123456789', N'456 Đường Kha Vạn Cân, Quận Thủ Đức, TP. HCM', N'Thường'),
(N'Phạm Văn Hồng', '0123456789', N'123 Đường Nguyễn Đình Chiểu, Quận 3, TP. HCM', N'VIP'),
(N'Đường Văn Bình', '0123456789', N'789 Đường Nam Kỳ Khởi Nghĩa, Quận 3, TP. HCM', N'VIP');
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

-- Dữ liệu cho bảng KhuyenMai
INSERT INTO KhuyenMai (ngayBatDau, ngayKetThuc, giamGia, dieuKien)
VALUES
('2024-07-01', '2024-07-31', 0.1, N'Áp dụng cho đơn hàng từ 500.000đ trở lên'),
('2024-08-05', '2024-08-20', 0.2, N'Áp dụng cho đơn hàng từ 1.000.000đ trở lên'),
('2024-09-10', '2024-09-30', 0.15, N'Áp dụng cho đơn hàng từ 700.000đ trở lên'),
('2024-10-01', '2024-10-15', 0.25, N'Áp dụng cho đơn hàng từ 800.000đ trở lên'),
('2024-11-05', '2024-11-20', 0.3, N'Áp dụng cho đơn hàng từ 1.500.000đ trở lên'),
('2024-12-01', '2024-12-31', 0.2, N'Áp dụng cho đơn hàng từ 600.000đ trở lên');

-- Dữ liệu cho bảng KhuVuc
INSERT INTO KhuVuc (tenKhuVuc)
VALUES
(N'Khu vực thực phẩm'), -- Bánh mì sandwich, Bánh quy sô cô la, Mì gói hảo hạng vị gà
(N'Khu vực thức uống'), -- Nước ngọt Coca-Cola 500ml, Nước trái cây ép ép táo, Nước suối Lavie 1.5L
(N'Khu vực đồ gia dụng'), -- Ống hút cỏ hình động vật, Ly thủy tinh cách nhiệt, Bát đĩa sứ họa tiết hoa, Bộ dụng cụ nấu ăn inox
(N'Khu vực dụng cụ học tập'), -- Bút mực nước cỡ lớn, Vở học sinh ô ly
(N'Khu vực thuốc lá'), -- Bật lửa mini, Gói thuốc lá Marlboro
(N'Khu vực phụ kiện điện tử'), -- Dây cáp sạc nhanh
(N'Khu vực phụ kiện thời trang'), -- Kính râm cận mắt
(N'Khu vực văn phòng phẩm'); -- Giấy photo in màu, Bút bi siêu mịn

-- dữ liệu bảng SanPham
INSERT INTO SanPham (maKhuyenMai, maKhuVuc, ten, giaSanPham, donVi, loaiSanPham, soLuongTonKho)
VALUES
(1003, 4000, N'Bánh mì sandwich thịt gà', 25000, N'Cái', N'Đồ ăn', 50),
(1004, 4001, N'Nước ngọt Coca-Cola 500ml', 15000, N'Cái', N'Thức uống', 30),
(1005, 4000, N'Bánh quy sô cô la', 35000, N'Gói', N'Đồ ăn', 10),
(1003, 4001, N'Nước trái cây ép táo', 25000, N'Cốc', N'Thức uống', 15),
(1001, 4000, N'Mì gói hảo hạng vị gà', 8000, N'Bịch', N'Đồ ăn', 25),
(1002, 4001, N'Nước suối Lavie 1.5L', 10000, N'Chai', N'Thức uống', 10),
(1003, 4002, N'Ống hút cỏ hình động vật', 30000, N'Cái', N'Đồ gia dụng', 50),
(1004, 4002, N'Ly thủy tinh cách nhiệt', 50000, N'Cái', N'Đồ gia dụng', 4),
(1005, 4002, N'Bát đĩa sứ họa tiết hoa', 120000, N'Bộ', N'Đồ gia dụng', 7),
(1000, 4002, N'Bộ dụng cụ nấu ăn inox', 250000, N'Bộ', N'Đồ gia dụng', 8),
(1000, 4003, N'Bút mực nước cỡ lớn', 20000, N'Cái', N'Dụng cụ học tập', 55),
(1001, 4003, N'Vở học sinh ô ly', 15000, N'Cái', N'Dụng cụ học tập', 67),
(1002, 4004, N'Bật lửa mini', 5000, N'Cái', N'Thuốc lá', 59),
(1004, 4004, N'Gói thuốc lá Marlboro', 35000, N'Gói', N'Thuốc lá', 34),
(1003, 4005, N'Dây cáp sạc nhanh', 50000, N'Cái', N'Phụ kiện điện tử', 4),
(1005, 4006, N'Kính râm cận mắt', 180000, N'Cặp', N'Phụ kiện thời trang', 10),
(1000, 4007, N'Giấy photo in màu', 50000, N'Cái', N'Văn phòng phẩm', 200),
(1003, 4007, N'Bút bi siêu mịn', 20000, N'Cái', N'Văn phòng phẩm', 115);
-- Dữ liệu cho bảng hóa đơn
INSERT INTO HoaDon (maKhachHang, maNhanVien, ngayMua, tongTien)
VALUES
(2000, 3000, '2024-05-18', 150000), -- Hóa đơn của khách hàng VIP, tổng tiền 150.000đ
(2001, 3001, '2024-05-19', 300000), -- Hóa đơn của khách hàng thường, tổng tiền 300.000đ
(2002, 3002, '2024-05-20', 550000), -- Hóa đơn của khách hàng VIP, tổng tiền 550.000đ
(2004, 3000, '2024-05-21', 450000), -- Hóa đơn của khách hàng VIP, tổng tiền 450.000đ
(2005, 3001, '2024-05-22', 180000), -- Hóa đơn của khách hàng thường, tổng tiền 180.000đ
(2006, 3002, '2024-05-23', 320000), -- Hóa đơn của khách hàng VIP, tổng tiền 320.000đ
(2008, 3000, '2024-05-24', 200000), -- Hóa đơn của khách hàng VIP, tổng tiền 200.000đ
(2009, 3001, '2024-05-25', 370000), -- Hóa đơn của khách hàng thường, tổng tiền 370.000đ
(2010, 3002, '2024-05-26', 480000), -- Hóa đơn của khách hàng VIP, tổng tiền 480.000đ
(2012, 3000, '2024-05-27', 220000), -- Hóa đơn của khách hàng VIP, tổng tiền 220.000đ
(2013, 3001, '2024-05-28', 290000), -- Hóa đơn của khách hàng thường, tổng tiền 290.000đ
(2014, 3002, '2024-05-29', 510000), -- Hóa đơn của khách hàng VIP, tổng tiền 510.000đ
(2016, 3000, '2024-05-30', 380000), -- Hóa đơn của khách hàng VIP, tổng tiền 380.000đ
(2017, 3001, '2024-05-31', 420000), -- Hóa đơn của khách hàng thường, tổng tiền 420.000đ
(2018, 3002, '2024-06-01', 600000), -- Hóa đơn của khách hàng VIP, tổng tiền 600.000đ
(2019, 3000, '2024-06-02', 280000), -- Hóa đơn của khách hàng VIP, tổng tiền 280.000đ
(2001, 3002, '2024-06-03', 350000), -- Hóa đơn của khách hàng thường, tổng tiền 350.000đ
(2019, 3002, '2024-06-04', 470000), -- Hóa đơn của khách hàng VIP, tổng tiền 470.000đ
(2018, 3000, '2024-06-05', 420000), -- Hóa đơn của khách hàng VIP, tổng tiền 420.000đ
(2003, 3001, '2024-06-06', 330000); -- Hóa đơn của khách hàng thường, tổng tiền 330.000đ

-- Dữ liệu cho bảng chi tiết hóa đơn với mã đơn từ 6076 đến 6095
INSERT INTO ChiTietHoaDon (maDon, maSanPham, thanhTien, soLuong)
VALUES
(6000, 5005, 70000, 2), -- 2 sản phẩm Bánh quy sô cô la, mỗi sản phẩm giá 35.000đ
(6000, 5007, 160000, 2), -- 2 sản phẩm Mì gói hảo hạng vị gà, mỗi sản phẩm giá 80.000đ
(6001, 5004, 140000, 2), -- 2 sản phẩm Nước ngọt Coca-Cola 500ml, mỗi sản phẩm giá 70.000đ
(6001, 5008, 20000, 1), -- 1 sản phẩm Nước suối Lavie 1.5L, giá 10.000đ
(6001, 5015, 70000, 1), -- 1 sản phẩm Gói thuốc lá Marlboro, giá 35.000đ
(6002, 5007, 160000, 2), -- 2 sản phẩm Mì gói hảo hạng vị gà, mỗi sản phẩm giá 80.000đ
(6002, 5009, 120000, 3), -- 3 sản phẩm Ống hút cỏ hình động vật, mỗi sản phẩm giá 40.000đ
(6003, 5010, 150000, 5), -- 5 sản phẩm Ly thủy tinh caách nhiệt, mỗi sản phẩm giá 30.000đ
(6003, 5011, 120000, 1), -- 1 sản phẩm Bát đĩa sứ họa tiết hoa, giá 120.000đ
(6003, 5012, 150000, 1), -- 1 sản phẩm Bộ dụng cụ nấu ăn inox, giá 150.000đ
(6004, 5013, 30000, 10), -- 10 sản phẩm Bút mực nước cỡ lớn, mỗi sản phẩm giá 3.000đ
(6004, 5014, 25000, 15); -- 15 sản phẩm Vở học sinh ô ly, mỗi sản phẩm giá 1.666đ

-- Dữ liệu cho bảng phiếu đặt
INSERT INTO PhieuDat (maNhanVien, nhaCungCap, ngayDat, tongTien)
VALUES
(3000, N'Công ty TNHH', '2024-05-18', 200000), -- Phiếu đặt từ công ty A, tổng tiền 200.000đ
(3001, N'Công ty SX Nước', '2024-05-19', 300000), -- Phiếu đặt từ công ty B, tổng tiền 300.000đ
(3002, N'Công ty C', '2024-05-20', 400000), -- Phiếu đặt từ công ty C, tổng tiền 400.000đ
(3000, N'Công ty SX Nước', '2024-05-21', 250000), -- Phiếu đặt từ công ty D, tổng tiền 250.000đ
(3001, N'Công ty VFOOD', '2024-05-22', 350000), -- Phiếu đặt từ công ty E, tổng tiền 350.000đ
(3002, N'Công ty WinMart', '2024-05-23', 450000), -- Phiếu đặt từ công ty F, tổng tiền 450.000đ
(3000, N'Công ty FarmFood', '2024-05-24', 180000), -- Phiếu đặt từ công ty G, tổng tiền 180.000đ
(3001, N'Công ty Lavi', '2024-05-25', 270000), -- Phiếu đặt từ công ty H, tổng tiền 270.000đ
(3002, N'Công ty Chánh Thắng', '2024-05-26', 320000), -- Phiếu đặt từ công ty I, tổng tiền 320.000đ
(3000, N'Công ty VNCLC', '2024-05-27', 290000), -- Phiếu đặt từ công ty J, tổng tiền 290.000đ
(3001, N'Công ty GoodFood', '2024-05-28', 210000), -- Phiếu đặt từ công ty K, tổng tiền 210.000đ
(3002, N'Công ty Intel', '2024-05-29', 180000), -- Phiếu đặt từ công ty L, tổng tiền 180.000đ
(3000, N'Công ty NVIDIA', '2024-05-30', 400000), -- Phiếu đặt từ công ty M, tổng tiền 400.000đ
(3001, N'Công ty HDD', '2024-05-31', 350000), -- Phiếu đặt từ công ty N, tổng tiền 350.000đ
(3002, N'Công ty OnLife', '2024-06-01', 550000), -- Phiếu đặt từ công ty O, tổng tiền 550.000đ
(3000, N'Công ty BigC', '2024-06-02', 480000), -- Phiếu đặt từ công ty P, tổng tiền 480.000đ
(3001, N'Công ty Copmart', '2024-06-03', 390000), -- Phiếu đặt từ công ty Q, tổng tiền 390.000đ
(3002, N'Công ty MetroMarker', '2024-06-04', 420000), -- Phiếu đặt từ công ty R, tổng tiền 420.000đ
(3000, N'Công ty NaVarious', '2024-06-05', 370000), -- Phiếu đặt từ công ty S, tổng tiền 370.000đ
(3001, N'Công ty GoodItem', '2024-06-06', 280000); -- Phiếu đặt từ công ty T, tổng tiền 280.000đ

-- Dữ liệu cho bảng chi tiết phiếu đặt
-- Phiếu đặt thứ ba
INSERT INTO ChiTietPhieuDat (maPhieu, maSanPham, thanhTien, soLuong)
VALUES
(7004, 5004, 140000, 2), -- 2 sản phẩm Nước ngọt Coca-Cola 500ml, mỗi sản phẩm giá 70.000đ
(7004, 5015, 70000, 1), -- 1 sản phẩm Gói thuốc lá Marlboro, giá 35.000đ
(7005, 5005, 70000, 2), -- 2 sản phẩm Bánh quy sô cô la, mỗi sản phẩm giá 35.000đ
(7005, 5008, 10000, 1), -- 1 sản phẩm Nước suối Lavie 1.5L, giá 10.000đ
(7006, 5007, 160000, 2), -- 2 sản phẩm Mì gói hảo hạng vị gà, mỗi sản phẩm giá 80.000đ
(7006, 5016, 35000, 1), -- 1 sản phẩm Gói thuốc lá Marlboro, giá 35.000đ
(7005, 5009, 120000, 3), -- 3 sản phẩm Ống hút cỏ hình động vật, mỗi sản phẩm giá 40.000đ
(7005, 5010, 150000, 5), -- 5 sản phẩm Ly thủy tinh cách nhiệt, mỗi sản phẩm giá 30.000đ
(7006, 5011, 240000, 2), -- 2 sản phẩm Bát đĩa sứ họa tiết hoa, mỗi sản phẩm giá 120.000đ
(7006, 5012, 450000, 3), -- 3 sản phẩm Bộ dụng cụ nấu ăn inox, mỗi sản phẩm giá 150.000đ
(7007, 5013, 30000, 10), -- 10 sản phẩm Bút mực nước cỡ lớn, mỗi sản phẩm giá 3.000đ
(7007, 5014, 25000, 15), -- 15 sản phẩm Vở học sinh ô ly, mỗi sản phẩm giá 1.666đ
(7008, 5015, 175000, 5), -- 5 sản phẩm Gói thuốc lá Marlboro, mỗi sản phẩm giá 35.000đ
(7008, 5016, 70000, 2); -- 2 sản phẩm Gói thuốc lá Marlboro, mỗi sản phẩm giá 35.000đ

-- Dữ liệu cho bảng KhuyenMai
INSERT INTO KhuyenMai (ngayBatDau, ngayKetThuc, giamGia, dieuKien)
VALUES
('2024-07-01', '2024-07-31', 0.1, N'Áp dụng cho đơn hàng từ 500.000đ trở lên'),
('2024-08-05', '2024-08-20', 0.2, N'Áp dụng cho đơn hàng từ 1.000.000đ trở lên'),
('2024-09-10', '2024-09-30', 0.15, N'Áp dụng cho đơn hàng từ 700.000đ trở lên'),
('2024-10-01', '2024-10-15', 0.25, N'Áp dụng cho đơn hàng từ 800.000đ trở lên'),
('2024-11-05', '2024-11-20', 0.3, N'Áp dụng cho đơn hàng từ 1.500.000đ trở lên'),
('2024-12-01', '2024-12-31', 0.2, N'Áp dụng cho đơn hàng từ 600.000đ trở lên');

-- procedure
go
create proc LocHoaDon
@Datestart date = null,
@DateEnd date = null,
@PriceStart money = -1,
@PriceEnd money = -1,
@MaKH int = -1,
@MaNV int = -1
as
begin
	select * 
	from [dbo].[HoaDon]
	where (@Datestart is null or ngayMua >= @Datestart) and
	(@DateEnd is null or ngayMua <= @DateEnd) and
	(@PriceStart = -1 or tongTien >= @PriceStart) and
	(@PriceEnd = -1 or tongTien < @PriceEnd) and
	(@MaKH = -1 or maKhachHang = @MaKH) and
	(@MaNV = -1 or maNhanVien = @MaNV)
end

--Lọc Phiếu đặt
go
create proc LocPhieuDat
@Datestart date = null,
@DateEnd date = null,
@PriceStart money = 0,
@PriceEnd money = 0,
@NCC nvarchar(50) = '',
@MaNV int = -1
as
begin
	select * 
	from [dbo].[PhieuDat]
	where (@Datestart is null or [ngayDat] >= @Datestart) and
	(@DateEnd is null or [ngayDat] <= @DateEnd) and
	(@PriceStart = 0 or tongTien >= @PriceStart) and
	(@PriceEnd = 0 or tongTien < @PriceEnd) and
	(@NCC = '' or [nhaCungCap] = @NCC) and
	(@MaNV = -1 or maNhanVien = @MaNV)
end

--Thêm Phiếu đặt
go
create proc addPhieuDat
@maNV int,
@NCC nvarchar(200),
@ngayDat date,
@tongTien money,
@maPhieuDat int out
as
begin
	insert into [dbo].[PhieuDat]
	values (
		@maNV,
		@NCC,
		@ngayDat,
		@tongTien
	)

	set @maPhieuDat = SCOPE_IDENTITY()
end


-- Lọc sản phẩm ở trang bán hàng
go
create PROCEDURE loc_SanPham
    @maSP int = NULL,
    @tenSP nvarchar(50),
    @loaiSP nvarchar(50) = NULL,
    @donVi nvarchar(50) = NULL
AS
	if(@maSP = -1)
		Set @maSP = null

	if(@loaiSP = '')
		Set @loaiSP = null

	if(@donVi = '')
		Set @donVi = null
    SELECT *
    FROM SanPham
    WHERE (@maSP IS NULL OR maSanPham = @maSP)
          AND (@tenSP IS NULL OR ten LIKE '%' + @tenSP + '%')
          AND (@loaiSP IS NULL OR loaiSanPham = @loaiSP)
          AND (@donVi IS NULL OR donVi = @donVi)
GO

--Thống kê sản doanh thu theo từng loại sản phẩm mà nhân viên X bán được
create proc doanhThuTheoLoaiSanPham
	@maNV int,
	@Datestart date = null,
	@DateEnd date = null
as
	select loaiSanPham, sum(thanhTien) as doanhThu
	from HoaDon hd join ChiTietHoaDon ct on hd.maDon = ct.maDon 
	join SanPham sp on ct.maSanPham = sp.maSanPham 
	where maNhanVien = @maNV and (@Datestart is null or ngayMua >= @Datestart) and
	(@DateEnd is null or ngayMua <= @DateEnd)
	group by loaiSanPham
go

create proc doanhThuTheoThoiGian
	@maNV int,
	@Datestart date = null,
	@DateEnd date = null
as
	select ngayMua, sum(thanhTien) as doanhThu
	from HoaDon hd join ChiTietHoaDon ct on hd.maDon = ct.maDon 
	join SanPham sp on ct.maSanPham = sp.maSanPham 
	where maNhanVien = @maNV and (@Datestart is null or ngayMua >= @Datestart) and
	(@DateEnd is null or ngayMua <= @DateEnd)
	group by ngayMua
go

-- Tìm sản phẩm có doanh thu cao nhất mà nhân viên X bán được
create proc sanPhamDoanhThuCaoNhat
	@maNV int,
	@Datestart date = null,
	@DateEnd date = null
as
	select Top 1 loaiSanPham
	from HoaDon hd join ChiTietHoaDon ct on hd.maDon = ct.maDon 
	join SanPham sp on ct.maSanPham = sp.maSanPham 
	where maNhanVien = @maNV and (@Datestart is null or ngayMua >= @Datestart) and
	(@DateEnd is null or ngayMua <= @DateEnd)
	group by loaiSanPham
	order by sum(thanhTien) desc
go

-- Tìm sản phẩm có doanh thu thấp nhất mà nhân viên X bán được
create proc sanPhamDoanhThuThapNhat
	@maNV int,
	@Datestart date = null,
	@DateEnd date = null
as
	select Top 1 loaiSanPham
	from HoaDon hd join ChiTietHoaDon ct on hd.maDon = ct.maDon 
	join SanPham sp on ct.maSanPham = sp.maSanPham 
	where maNhanVien = @maNV and (@Datestart is null or ngayMua >= @Datestart) and
	(@DateEnd is null or ngayMua <= @DateEnd)
	group by loaiSanPham
	order by sum(thanhTien)
go

--Thống kê doanh thu cửa hàng bán được theo ngày
create proc doanhThuTheoNgay
	@month int,
	@year int
as
	select day(ngayMua), sum(thanhTien) as doanhThu
	from HoaDon hd join ChiTietHoaDon ct on hd.maDon = ct.maDon 
	where month(ngayMua) = @month and year(ngayMua) = @year
	group by day(ngayMua)
go

--Thống kê doanh thu cửa hàng bán được theo Tháng
create proc doanhThuTheoThang
	@year int
as
	select month(ngayMua), sum(thanhTien) as doanhThu
	from HoaDon hd join ChiTietHoaDon ct on hd.maDon = ct.maDon 
	where year(ngayMua) = @year
	group by month(ngayMua)
go

--Thống kê Chi Phí cửa hàng đặt theo ngày
create proc ChiPhiTheoNgay
	@month int,
	@year int
as
	select day(ngayDat), sum(tongTien) as doanhThu
	from PhieuDat pd join ChiTietPhieuDat ct on pd.maPhieu = ct.maPhieu 
	where month(ngayDat) = @month and year(ngayDat) = @year
	group by day(ngayDat)
go

--Thống kê doanh thu cửa hàng bán được theo Tháng
create proc ChiPhiTheoThang
	@year int
as
	select month(ngayDat), sum(tongTien) as doanhThu
	from PhieuDat pd join ChiTietPhieuDat ct on pd.maPhieu = ct.maPhieu 
	where year(ngayDat) = @year
	group by month(ngayDat)
go

