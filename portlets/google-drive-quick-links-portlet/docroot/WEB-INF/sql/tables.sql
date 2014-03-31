create table rivetlogic_DriveLinks (
	USER_ID VARCHAR(75) not null,
	NAME VARCHAR(75) not null,
	URL VARCHAR(150) null,
	primary key (USER_ID, NAME)
);