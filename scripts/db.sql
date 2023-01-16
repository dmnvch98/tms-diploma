select language_id, level_id, l.description, l.id from language_levels ll
            join user_language_levels ull on ll.id = ull.language_level_id
            join languages l on l.id = ll.language_id
            where user_id=15;

select * from language_levels ll
join languages l on l.id = ll.language_id
join levels l2 on l2.id = ll.level_id
where ll.id = 6
