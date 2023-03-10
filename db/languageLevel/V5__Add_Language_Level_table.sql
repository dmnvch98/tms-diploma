create table language_levels
(
    language_level_id          BIGSERIAL NOT NULL PRIMARY KEY,
    language_id BIGSERIAL NOT NULL,
    level_id    BIGSERIAL NOT NULL,

    UNIQUE (language_id, level_id),
    FOREIGN KEY (language_id) REFERENCES languages (language_id),
    FOREIGN KEY (level_id) REFERENCES levels (level_id)
);

insert into language_levels(language_id, level_id) values ('1','1');
insert into language_levels(language_id, level_id) values ('1','2');
insert into language_levels(language_id, level_id) values ('1','3');
insert into language_levels(language_id, level_id) values ('1','4');
insert into language_levels(language_id, level_id) values ('1','5');
insert into language_levels(language_id, level_id) values ('1','6');
insert into language_levels(language_id, level_id) values ('2','1');
insert into language_levels(language_id, level_id) values ('2','2');
insert into language_levels(language_id, level_id) values ('2','3');
insert into language_levels(language_id, level_id) values ('2','4');
insert into language_levels(language_id, level_id) values ('2','5');
insert into language_levels(language_id, level_id) values ('2','6');
insert into language_levels(language_id, level_id) values ('3','1');
insert into language_levels(language_id, level_id) values ('3','2');
insert into language_levels(language_id, level_id) values ('3','3');
insert into language_levels(language_id, level_id) values ('3','4');
insert into language_levels(language_id, level_id) values ('3','5');
insert into language_levels(language_id, level_id) values ('3','6');
insert into language_levels(language_id, level_id) values ('4','1');
insert into language_levels(language_id, level_id) values ('4','2');
insert into language_levels(language_id, level_id) values ('4','3');
insert into language_levels(language_id, level_id) values ('4','4');
insert into language_levels(language_id, level_id) values ('4','5');
insert into language_levels(language_id, level_id) values ('4','6');
insert into language_levels(language_id, level_id) values ('5','1');
insert into language_levels(language_id, level_id) values ('5','2');
insert into language_levels(language_id, level_id) values ('5','3');
insert into language_levels(language_id, level_id) values ('5','4');
insert into language_levels(language_id, level_id) values ('5','5');
insert into language_levels(language_id, level_id) values ('5','6');
insert into language_levels(language_id, level_id) values ('6','1');
insert into language_levels(language_id, level_id) values ('6','2');
insert into language_levels(language_id, level_id) values ('6','3');
insert into language_levels(language_id, level_id) values ('6','4');
insert into language_levels(language_id, level_id) values ('6','5');
insert into language_levels(language_id, level_id) values ('6','6');
insert into language_levels(language_id, level_id) values ('7','1');
insert into language_levels(language_id, level_id) values ('7','2');
insert into language_levels(language_id, level_id) values ('7','3');
insert into language_levels(language_id, level_id) values ('7','4');
insert into language_levels(language_id, level_id) values ('7','5');
insert into language_levels(language_id, level_id) values ('7','6');
insert into language_levels(language_id, level_id) values ('8','1');
insert into language_levels(language_id, level_id) values ('8','2');
insert into language_levels(language_id, level_id) values ('8','3');
insert into language_levels(language_id, level_id) values ('8','4');
insert into language_levels(language_id, level_id) values ('8','5');
insert into language_levels(language_id, level_id) values ('8','6');
insert into language_levels(language_id, level_id) values ('9','1');
insert into language_levels(language_id, level_id) values ('9','2');
insert into language_levels(language_id, level_id) values ('9','3');
insert into language_levels(language_id, level_id) values ('9','4');
insert into language_levels(language_id, level_id) values ('9','5');
insert into language_levels(language_id, level_id) values ('9','6');
insert into language_levels(language_id, level_id) values ('10','1');
insert into language_levels(language_id, level_id) values ('10','2');
insert into language_levels(language_id, level_id) values ('10','3');
insert into language_levels(language_id, level_id) values ('10','4');
insert into language_levels(language_id, level_id) values ('10','5');
insert into language_levels(language_id, level_id) values ('10','6');
insert into language_levels(language_id, level_id) values ('11','1');
insert into language_levels(language_id, level_id) values ('11','2');
insert into language_levels(language_id, level_id) values ('11','3');
insert into language_levels(language_id, level_id) values ('11','4');
insert into language_levels(language_id, level_id) values ('11','5');
insert into language_levels(language_id, level_id) values ('11','6');
insert into language_levels(language_id, level_id) values ('12','1');
insert into language_levels(language_id, level_id) values ('12','2');
insert into language_levels(language_id, level_id) values ('12','3');
insert into language_levels(language_id, level_id) values ('12','4');
insert into language_levels(language_id, level_id) values ('12','5');
insert into language_levels(language_id, level_id) values ('12','6');
insert into language_levels(language_id, level_id) values ('13','1');
insert into language_levels(language_id, level_id) values ('13','2');
insert into language_levels(language_id, level_id) values ('13','3');
insert into language_levels(language_id, level_id) values ('13','4');
insert into language_levels(language_id, level_id) values ('13','5');
insert into language_levels(language_id, level_id) values ('13','6');
insert into language_levels(language_id, level_id) values ('14','1');
insert into language_levels(language_id, level_id) values ('14','2');
insert into language_levels(language_id, level_id) values ('14','3');
insert into language_levels(language_id, level_id) values ('14','4');
insert into language_levels(language_id, level_id) values ('14','5');
insert into language_levels(language_id, level_id) values ('14','6');
insert into language_levels(language_id, level_id) values ('15','1');
insert into language_levels(language_id, level_id) values ('15','2');
insert into language_levels(language_id, level_id) values ('15','3');
insert into language_levels(language_id, level_id) values ('15','4');
insert into language_levels(language_id, level_id) values ('15','5');
insert into language_levels(language_id, level_id) values ('15','6');
insert into language_levels(language_id, level_id) values ('16','1');
insert into language_levels(language_id, level_id) values ('16','2');
insert into language_levels(language_id, level_id) values ('16','3');
insert into language_levels(language_id, level_id) values ('16','4');
insert into language_levels(language_id, level_id) values ('16','5');
insert into language_levels(language_id, level_id) values ('16','6');
insert into language_levels(language_id, level_id) values ('17','1');
insert into language_levels(language_id, level_id) values ('17','2');
insert into language_levels(language_id, level_id) values ('17','3');
insert into language_levels(language_id, level_id) values ('17','4');
insert into language_levels(language_id, level_id) values ('17','5');
insert into language_levels(language_id, level_id) values ('17','6');
insert into language_levels(language_id, level_id) values ('18','1');
insert into language_levels(language_id, level_id) values ('18','2');
insert into language_levels(language_id, level_id) values ('18','3');
insert into language_levels(language_id, level_id) values ('18','4');
insert into language_levels(language_id, level_id) values ('18','5');
insert into language_levels(language_id, level_id) values ('18','6');
insert into language_levels(language_id, level_id) values ('19','1');
insert into language_levels(language_id, level_id) values ('19','2');
insert into language_levels(language_id, level_id) values ('19','3');
insert into language_levels(language_id, level_id) values ('19','4');
insert into language_levels(language_id, level_id) values ('19','5');
insert into language_levels(language_id, level_id) values ('19','6');
insert into language_levels(language_id, level_id) values ('20','1');
insert into language_levels(language_id, level_id) values ('20','2');
insert into language_levels(language_id, level_id) values ('20','3');
insert into language_levels(language_id, level_id) values ('20','4');
insert into language_levels(language_id, level_id) values ('20','5');
insert into language_levels(language_id, level_id) values ('20','6');
insert into language_levels(language_id, level_id) values ('21','1');
insert into language_levels(language_id, level_id) values ('21','2');
insert into language_levels(language_id, level_id) values ('21','3');
insert into language_levels(language_id, level_id) values ('21','4');
insert into language_levels(language_id, level_id) values ('21','5');
insert into language_levels(language_id, level_id) values ('21','6');
insert into language_levels(language_id, level_id) values ('22','1');
insert into language_levels(language_id, level_id) values ('22','2');
insert into language_levels(language_id, level_id) values ('22','3');
insert into language_levels(language_id, level_id) values ('22','4');
insert into language_levels(language_id, level_id) values ('22','5');
insert into language_levels(language_id, level_id) values ('22','6');
insert into language_levels(language_id, level_id) values ('23','1');
insert into language_levels(language_id, level_id) values ('23','2');
insert into language_levels(language_id, level_id) values ('23','3');
insert into language_levels(language_id, level_id) values ('23','4');
insert into language_levels(language_id, level_id) values ('23','5');
insert into language_levels(language_id, level_id) values ('23','6');
insert into language_levels(language_id, level_id) values ('24','1');
insert into language_levels(language_id, level_id) values ('24','2');
insert into language_levels(language_id, level_id) values ('24','3');
insert into language_levels(language_id, level_id) values ('24','4');
insert into language_levels(language_id, level_id) values ('24','5');
insert into language_levels(language_id, level_id) values ('24','6');
