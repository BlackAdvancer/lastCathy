drop table Item CASCADE constraints;
drop table Branch CASCADE constraints;
drop table Clerk CASCADE constraints;
drop table Storage;
drop table Deal CASCADE constraints;
drop table ItemsInPurchase;
drop table Offer;
drop table Purchase CASCADE constraints;
drop table ItemsInDeal;
drop table Membership CASCADE constraints;
drop table MemberPurchase;

CREATE TABLE Item
(
	itemID INTEGER PRIMARY KEY,
	name CHAR(20),
	price NUMBER(6, 2),
	type CHAR(20)
);

grant select on Item to public;

CREATE TABLE Branch (
	branchNumber INTEGER PRIMARY KEY,
	location CHAR(100)
);

grant select on Branch to public;

CREATE TABLE Clerk (
	clerkID INTEGER PRIMARY KEY,
	name CHAR(20),
	wage INTEGER,
	branchNumber INTEGER NOT NULL,
	type CHAR(20),
	FOREIGN KEY (branchNumber) REFERENCES Branch
		ON DELETE CASCADE
);

grant select on Clerk to public;

CREATE TABLE Storage (
	itemID INTEGER,
	branchNumber INTEGER NOT NULL,
	amount INTEGER,
	primary key (itemID, branchNumber),
	FOREIGN KEY (itemID) REFERENCES Item
		ON DELETE CASCADE	,
	FOREIGN KEY (branchNumber) REFERENCES Branch
		ON DELETE CASCADE
);



grant select on Storage to public;

CREATE TABLE Deal (
	dealName CHAR(20) PRIMARY KEY,
	duration CHAR(20)
);

grant select on Deal to public;

CREATE TABLE ItemsInDeal (
	itemID INTEGER NOT NULL,
	dealName CHAR(20),
	percentage NUMBER(3, 2),
	primary key (itemID, dealName),
	FOREIGN KEY (itemID) REFERENCES Item
		ON DELETE CASCADE,
	FOREIGN KEY (dealName) REFERENCES Deal
		ON DELETE CASCADE
);

grant select on ItemsInDeal to public;

CREATE TABLE Offer (
	dealName CHAR(20),
	branchNumber INTEGER NOT NULL,
	primary key (dealName, branchNumber),
	FOREIGN KEY (dealName) REFERENCES Deal
		ON DELETE CASCADE,
	FOREIGN KEY (branchNumber) REFERENCES Branch
		ON DELETE CASCADE
);

grant select on Offer to public;

CREATE TABLE Purchase (
	receiptNumber INTEGER PRIMARY KEY,
	purchaseTime CHAR(20),
	purchaseDate CHAR(20),
	totalPrice NUMBER(5, 2) NOT NULL,
	clerkID INTEGER NOT NULL,
	branchNumber INTEGER NOT NULL,
	FOREIGN KEY (clerkID) REFERENCES Clerk,
	FOREIGN KEY (branchNumber) REFERENCES Branch
);

grant select on Purchase to public;

CREATE TABLE ItemsInPurchase (
	receiptNumber INTEGER,
	itemID INTEGER NOT NULL,
	amount INTEGER NOT NULL,
	primary key(receiptNumber, itemID),
	FOREIGN KEY (receiptNumber) REFERENCES Purchase,
	FOREIGN KEY (itemID) REFERENCES Item
);

grant select on ItemsInPurchase to public;

CREATE TABLE Membership (
	memberID INTEGER PRIMARY KEY,
	name CHAR(20),
	phone CHAR(20),
	points INTEGER,
	UNIQUE (name, phone)
);

grant select on Membership to public;

CREATE TABLE MemberPurchase (
	receiptNumber INTEGER PRIMARY KEY,
	memberID INTEGER NOT NULL,
	FOREIGN KEY (receiptNumber) REFERENCES Purchase
		ON DELETE CASCADE,
	FOREIGN KEY (memberID) REFERENCES Membership
		ON DELETE CASCADE
);

grant select on MemberPurchase to public;

insert into Branch (branchNumber, location) values (1, '8777 Thackeray Drive');
insert into Branch (branchNumber, location) values (2, '36564 Mcguire Trail');
insert into Branch (branchNumber, location) values (3, '466 Wayridge Terrace');
insert into Branch (branchNumber, location) values (4, '45 Knutson Pass');
insert into Branch (branchNumber, location) values (5, '13658 Kipling Street');

insert into Clerk (clerkID, name, wage, branchNumber, type) values (1252, 'Osbourn Borsay', 21, 5, 'Manager');
insert into Clerk (clerkID, name, wage, branchNumber, type) values (1045, 'Sheryl Luce', 26, 2, 'Employee');
insert into Clerk (clerkID, name, wage, branchNumber, type) values (1144, 'Willa Maciejak', 15, 4, 'Employee');
insert into Clerk (clerkID, name, wage, branchNumber, type) values (1040, 'Helen Tulip', 27, 1, 'Employee');
insert into Clerk (clerkID, name, wage, branchNumber, type) values (1093, 'Petronia Stretton', 28, 2, 'Employee');
insert into Clerk (clerkID, name, wage, branchNumber, type) values (1141, 'Ora McCarthy', 30, 3, 'Employee');
insert into Clerk (clerkID, name, wage, branchNumber, type) values (1338, 'Araldo Banaszewski', 28, 2, 'Employee');
insert into Clerk (clerkID, name, wage, branchNumber, type) values (1429, 'Peter Creaney', 27, 1, 'Employee');
insert into Clerk (clerkID, name, wage, branchNumber, type) values (1441, 'Venus Bodesson', 28, 5, 'Employee');
insert into Clerk (clerkID, name, wage, branchNumber, type) values (1456, 'Augy De Lorenzo', 29, 3, 'Employee');

insert into Item (itemId, name, price, type) values (0744, 'Happy Nerd Water', 1.65, 'Drink');
insert into Item (itemId, name, price, type) values (0745, 'Humor Frozen Pizza', 9.99, 'Food');
insert into Item (itemId, name, price, type) values (0746, 'Durex Conodom', 15.99, 'Commodity');
insert into Item (itemId, name, price, type) values (0747, 'Fuel', 1.65,  'Service');
insert into Item (itemId, name, price, type) values (0748, 'LaoGanMa Sauce', 7, 'Food');

insert into Deal (dealName, duration) values ('Spring Sale', '02-01 - 02-05');
insert into Deal (dealName, duration) values ('Summer Sale', '07-01 - 07-03');
insert into Deal (dealName, duration) values ('Christmas Sale', '12-25 - 12-31');
insert into Deal (dealName, duration) values ('Canada Day Deal', '07-01 - 07-01');
insert into Deal (dealName, duration) values ('Winter Sale', '01-01 - 01-05');

insert into Offer (dealName, branchNumber) values ('Summer Sale', 1);
insert into Offer (dealName, branchNumber) values ('Summer Sale', 2);
insert into Offer (dealName, branchNumber) values ('Summer Sale', 3);
insert into Offer (dealName, branchNumber) values ('Spring Sale', 4);
insert into Offer (dealName, branchNumber) values ('Spring Sale', 3);
insert into Offer (dealName, branchNumber) values ('Spring Sale', 2);
insert into Offer (dealName, branchNumber) values ('Christmas Sale', 1);
insert into Offer (dealName, branchNumber) values ('Christmas Sale', 2);
insert into Offer (dealName, branchNumber) values ('Christmas Sale', 3);
insert into Offer (dealName, branchNumber) values ('Christmas Sale', 5);

insert into ItemsInDeal (itemID, dealName, percentage) values (0745, 'Christmas Sale', 0.5);
insert into ItemsInDeal (itemID, dealName, percentage) values (0747, 'Spring Sale', 0.2);
insert into ItemsInDeal (itemID, dealName, percentage) values (0744, 'Spring Sale', 0.25);
insert into ItemsInDeal (itemID, dealName, percentage) values (0745, 'Summer Sale', 0.10);
insert into ItemsInDeal (itemID, dealName, percentage) values (0748, 'Summer Sale', 0.30);

insert into MemberShip (memberID, name, phone, points) values (1270, 'Aurelie Dibb', '266-901-5451', 28);
insert into MemberShip (memberID, name, phone, points) values (1349, 'Ingunna Tytherton', '563-282-7211', 68);
insert into MemberShip (memberID, name, phone, points) values (1088, 'Kenny Burkill', '632-473-2922', 96);
insert into MemberShip (memberID, name, phone, points) values (1023, 'Malina Bone', '725-347-0973', 218);
insert into MemberShip (memberID, name, phone, points) values (1055, 'Craggy Trott', '402-733-1676', 154);

insert into Purchase (receiptNumber, purchaseDate, purchaseTime, TotalPrice, clerkID, BranchNumber) values (3165, '20-02', '22:15', 43.77, 1252, 1);
insert into Purchase (receiptNumber, purchaseDate, purchaseTime, TotalPrice, clerkID, BranchNumber) values (3304, '30-01', '02:30', 95.43, 1252, 1);
insert into Purchase (receiptNumber, purchaseDate, purchaseTime, TotalPrice, clerkID, BranchNumber) values (3051, '15-02', '9:15', 99.48, 1040, 2);
insert into Purchase (receiptNumber, purchaseDate, purchaseTime, TotalPrice, clerkID, BranchNumber) values (3120, '17-04', '10:00', 60.7, 1429, 2);
insert into Purchase (receiptNumber, purchaseDate, purchaseTime, TotalPrice, clerkID, BranchNumber) values (3155, '12-01', '11:14', 28.25, 1093, 3);
insert into Purchase (receiptNumber, purchaseDate, purchaseTime, TotalPrice, clerkID, BranchNumber) values (3083, '14-11', '19:45', 78.97, 1429, 4);
insert into Purchase (receiptNumber, purchaseDate, purchaseTime, TotalPrice, clerkID, BranchNumber) values (3428, '23-12', '06:01', 65.73, 1441, 5);
insert into Purchase (receiptNumber, purchaseDate, purchaseTime, TotalPrice, clerkID, BranchNumber) values (3209, '11-42', '14:20', 27.54, 1429, 4);


insert into MemberPurchase (receiptNumber, memberID) values (3209, 1349);
insert into MemberPurchase (receiptNumber, memberID) values (3120, 1088);
insert into MemberPurchase (receiptNumber, memberID) values (3051, 1023);
insert into MemberPurchase (receiptNumber, memberID) values (3304, 1023);
insert into MemberPurchase (receiptNumber, memberID) values (3165, 1055);

insert into Storage (itemId, branchNumber, amount) values (0744, 1, 150);
insert into Storage (itemId, branchNumber, amount) values (0745, 1, 200);
insert into Storage (itemId, branchNumber, amount) values (0745, 2, 1500);
insert into Storage (itemId, branchNumber, amount) values (0746, 1, 176);
insert into Storage (itemId, branchNumber, amount) values (0747, 1, 45);
insert into Storage (itemId, branchNumber, amount) values (0747, 3, 32);
insert into Storage (itemId, branchNumber, amount) values (0748, 1, 160);
insert into Storage (itemId, branchNumber, amount) values (0748, 5, 1503);

insert into ItemsInPurchase (receiptNumber, itemID, amount) values (3165, 744, 10);
insert into ItemsInPurchase (receiptNumber, itemID, amount) values (3165, 745, 10);
insert into ItemsInPurchase (receiptNumber, itemID, amount) values (3165, 747, 10);
insert into ItemsInPurchase (receiptNumber, itemID, amount) values (3165, 748, 5);
insert into ItemsInPurchase (receiptNumber, itemID, amount) values (3304, 744, 20);
insert into ItemsInPurchase (receiptNumber, itemID, amount) values (3155, 745, 5);
insert into ItemsInPurchase (receiptNumber, itemID, amount) values (3083, 745, 2);
insert into ItemsInPurchase (receiptNumber, itemID, amount) values (3428, 744, 10);


commit;