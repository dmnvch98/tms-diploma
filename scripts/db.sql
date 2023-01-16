select language_id, level_id, l.description, l.id from language_levels ll
            join user_language_levels ull on ll.id = ull.language_level_id
            join languages l on l.id = ll.language_id
            where user_id=15;

select * from language_levels ll
join languages l on l.id = ll.language_id
join levels l2 on l2.id = ll.level_id
where ll.id = 6

    SELECT ll.language_level_id AS language_level_id, l.level_id AS level_level_id, l.description
            AS level_description, lg.language_id AS language_language_id, lg.description AS language_description  
            FROM language_levels ll
            LEFT OUTER JOIN levels l ON l.level_id = ll.level_id
            LEFT OUTER JOIN languages lg ON lg.language_id = ll.language_id
            LEFT OUTER JOIN user_language_levels ull on ll.language_level_id = ull.language_level_id
            WHERE ull.user_id=:userId