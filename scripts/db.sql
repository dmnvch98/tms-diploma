select u.first_name, u.last_name, l.description, l2.description
from users u
         join user_language_levels ull on u.id = ull.user_id
         join language_levels ll on ll.id = ull.language_level_id
         join languages l on l.id = ll.language_id
         join levels l2 on l2.id = ll.level_id;