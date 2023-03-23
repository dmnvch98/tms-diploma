create table users
(
    id            BIGSERIAL NOT NULL PRIMARY KEY,
    first_name    TEXT      NOT NULL,
    last_name     TEXT      NOT NULL,
    email         TEXT      NOT NULL,
    password      TEXT      NOT NULL,
    nationality   BIGSERIAL NOT NULL,
    location      TEXT,
    roles         TEXT[]    NOT NULL,
    gender        TEXT      NOT NULL,
    refresh_token TEXT,
    avatar_name   TEXT,

    UNIQUE (email),
    FOREIGN KEY (nationality) REFERENCES countries (country_id)
);

insert into users (first_name, last_name, email, gender, password, location, roles) values ('Vern', 'Caines', 'vcaines0@gov.uk', 'Male', 'InTVmAol1', 'Naka', '{TUTOR}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Gerard', 'Weatherley', 'gweatherley1@clickbank.net', 'Male', 'eXmNXUU', 'San Celestio', '{STUDENT}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Nannie', 'Dovington', 'ndovington2@booking.com', 'Female', 'onZN4ml', 'Zhixia', '{TUTOR}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Liza', 'Keets', 'lkeets3@exblog.jp', 'Female', 'nkG0TVva0BX', 'Cotuí', '{STUDENT}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Nealson', 'Jakuszewski', 'njakuszewski4@vinaora.com', 'Male', 'SFqYGEzQx', 'Mūsá Qal‘ah', '{STUDENT}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Ira', 'Scotchmor', 'iscotchmor5@ezinearticles.com', 'Male', 'KwyxYS', 'Niebylec', '{TUTOR}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Shayne', 'Lanphier', 'slanphier6@guardian.co.uk', 'Female', 'zS8e5nIyfgzM', 'Sangkulirang', '{STUDENT}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Nola', 'Commings', 'ncommings7@privacy.gov.au', 'Female', 'W7utCNmN7MnY', 'Olofström', '{TUTOR}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Granville', 'Natt', 'gnatt8@nydailynews.com', 'Male', 'Z1PTSkxhAa6y', 'Calinog', '{STUDENT}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Inga', 'Richarz', 'iricharz9@webeden.co.uk', 'Female', '1tpQttMX', 'Casigua El Cubo', '{TUTOR}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Averil', 'Skipperbottom', 'askipperbottoma@de.vu', 'Male', 'KSv81q', 'Alexandria', '{TUTOR}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Gayelord', 'Works', 'gworksb@indiatimes.com', 'Male', 'evdI5rL5', 'Nanchoc', '{STUDENT}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Emory', 'Davidek', 'edavidekc@homestead.com', 'Male', 'hUbLoKWDCFO', 'Innisfil', '{TUTOR}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Nona', 'Toffanelli', 'ntoffanellid@discuz.net', 'Female', 'PseGFkOic1DP', 'Tangshan', '{STUDENT}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Damien', 'Kermitt', 'dkermitte@yellowbook.com', 'Agender', 'sYogOqCxU', 'Energeticheskiy', '{STUDENT}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Jocko', 'Polden', 'jpoldenf@csmonitor.com', 'Male', 'Ey1ObQG', 'Shidong', '{STUDENT}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Halette', 'Tedridge', 'htedridgeg@si.edu', 'Polygender', '7uxkiBA', 'Liangnong', '{TUTOR}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Deanna', 'Ormston', 'dormstonh@google.co.jp', 'Female', 'AuVckpgWP2', 'Kota Kinabalu', '{TUTOR}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Pancho', 'Bales', 'pbalesi@usatoday.com', 'Male', 'qrXGSsx', 'Xiatuanpu', '{TUTOR}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Opal', 'Dimanche', 'odimanchej@tumblr.com', 'Female', 'YcZofr', 'Camp Diable', '{STUDENT}');