INSERT INTO `super_rent`.`customer`
(
`PhoneNo`,
`FirstName`,
`MiddleName`,
`LastName`,
`Email`,
`Address`,
`DriverLicenseNo`,
`IsClubMember`,
`MemberPoint`,
`MembershipExpireDate`,
`Username`
)
VALUES
(
"1234567890",
"FirstName",
"MiddleName",
"LastName",
"Email",
"Address",
"888888888",
false,
0,
0,
"customer"
);


INSERT INTO `super_rent`.`customer`
(
`PhoneNo`,
`FirstName`,
`MiddleName`,
`LastName`,
`Email`,
`Address`,
`DriverLicenseNo`,
`IsClubMember`,
`MemberPoint`,
`MembershipExpireDate`,
`Username`
)
VALUES
(
"1234567891",
"FirstName2",
"MiddleName2",
"LastName2",
"Email2",
"Address2",
"888888888",
true,
500,
0,
"clubmember"
);

#########################################

INSERT INTO `super_rent`.`staff`
(`FirstName`,
`MiddleName`,
`LastName`,
`Email`,
`PhoneNo`,
`Type`,
`Status`,
`Username`,
`BranchID`)
VALUES
("clerk_f",
"clerk_m",
"clerk_l",
"clerk@scr.com",
"123456",
"CLERK",
"ACTIVE",
"clerk",
1
);

INSERT INTO `super_rent`.`staff`
(`FirstName`,
`MiddleName`,
`LastName`,
`Email`,
`PhoneNo`,
`Type`,
`Status`,
`Username`,
`BranchID`)
VALUES
("manager_f",
"manager_m",
"manager_l",
"manager@scr.com",
"123456",
"MANAGER",
"ACTIVE",
"manager",
1
);


