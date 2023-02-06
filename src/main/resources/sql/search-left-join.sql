select tb_wang_menu.* from tb_wang_menu
    left join tb_wang_role_menu_relation t1 on tb_wang_menu.id = t1.menu_id
    left join tb_wang_role t2 on t1.role_id = t2.id
    left join tb_wang_user_role_relation t3 on t2.id = t3.role_id
    left join tb_wang_user user on t3.user_id = user.id
    where user.id = 19;