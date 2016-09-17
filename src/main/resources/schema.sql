create table if not exists CUSTOMER 
(
	id bigint not null,
	email varchar(255) not null,
	last_name varchar(100) not null,
	name varchar(100) not null,
	primary key (id)
);

INSERT INTO CUSTOMER(id,name,last_name,email) VALUES (1, 'Hakan', 'KABASAKAL', 'kbhkn@bil.omu.edu.tr');
INSERT INTO CUSTOMER(id,name,last_name,email) VALUES (2, 'Ali Han', 'GÜZEL', 'alhngzl@gmail.com');
INSERT INTO CUSTOMER(id,name,last_name,email) VALUES (3, 'Kerem', 'HACIFAZLIOĞLU', 'kerem.hacifazlioglu@bil.omu.edu.tr');
