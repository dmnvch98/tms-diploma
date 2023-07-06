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

insert into users (first_name, last_name, email, gender, password, location, roles) values ('Vern', 'Caines', 'vcaines0@gov.uk', 'Male', 'InTVmAol1', 'Naka', '{Tutor}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Gerard', 'Weatherley', 'gweatherley1@clickbank.net', 'Male', 'eXmNXUU', 'San Celestio', '{Student}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Nannie', 'Dovington', 'ndovington2@booking.com', 'Female', 'onZN4ml', 'Zhixia', '{Tutor}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Liza', 'Keets', 'lkeets3@exblog.jp', 'Female', 'nkG0TVva0BX', 'Cotuí', '{Student}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Nealson', 'Jakuszewski', 'njakuszewski4@vinaora.com', 'Male', 'SFqYGEzQx', 'Mūsá Qal‘ah', '{Student}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Ira', 'Scotchmor', 'iscotchmor5@ezinearticles.com', 'Male', 'KwyxYS', 'Niebylec', '{Tutor}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Shayne', 'Lanphier', 'slanphier6@guardian.co.uk', 'Female', 'zS8e5nIyfgzM', 'Sangkulirang', '{Student}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Nola', 'Commings', 'ncommings7@privacy.gov.au', 'Female', 'W7utCNmN7MnY', 'Olofström', '{Tutor}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Granville', 'Natt', 'gnatt8@nydailynews.com', 'Male', 'Z1PTSkxhAa6y', 'Calinog', '{Student}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Inga', 'Richarz', 'iricharz9@webeden.co.uk', 'Female', '1tpQttMX', 'Casigua El Cubo', '{Tutor}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Averil', 'Skipperbottom', 'askipperbottoma@de.vu', 'Male', 'KSv81q', 'Alexandria', '{Tutor}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Gayelord', 'Works', 'gworksb@indiatimes.com', 'Male', 'evdI5rL5', 'Nanchoc', '{Student}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Emory', 'Davidek', 'edavidekc@homestead.com', 'Male', 'hUbLoKWDCFO', 'Innisfil', '{Tutor}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Nona', 'Toffanelli', 'ntoffanellid@discuz.net', 'Female', 'PseGFkOic1DP', 'Tangshan', '{Student}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Damien', 'Kermitt', 'dkermitte@yellowbook.com', 'Agender', 'sYogOqCxU', 'Energeticheskiy', '{Student}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Jocko', 'Polden', 'jpoldenf@csmonitor.com', 'Male', 'Ey1ObQG', 'Shidong', '{Student}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Halette', 'Tedridge', 'htedridgeg@si.edu', 'Polygender', '7uxkiBA', 'Liangnong', '{Tutor}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Deanna', 'Ormston', 'dormstonh@google.co.jp', 'Female', 'AuVckpgWP2', 'Kota Kinabalu', '{Tutor}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Pancho', 'Bales', 'pbalesi@usatoday.com', 'Male', 'qrXGSsx', 'Xiatuanpu', '{Tutor}');
insert into users (first_name, last_name, email, gender, password, location, roles) values ('Opal', 'Dimanche', 'odimanchej@tumblr.com', 'Female', 'YcZofr', 'Camp Diable', '{Student}');