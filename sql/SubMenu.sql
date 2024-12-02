DROP TABLE IF EXISTS file_storage.submenu;
CREATE TABLE file_storage.submenu (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Auto-incrementing primary key
    title VARCHAR(50)  NULL,        -- Title cannot be NULL, with a maximum length of 255 characters
    path VARCHAR(24)  NULL,      -- Path cannot be NULL, with a maximum length of 255 characters
    mid INT NOT NULL   -- associated property of mainmenu id
);
INSERT INTO file_storage.submenu (title, path, mid) VALUES
('用户列表', '/user', 100),
('修改权限', '/rights', 100),
('播放器', '/player', 200),
('音乐列表','/mlist',200);
-- 添加图标名称字段
ALTER TABLE file_storage.submenu ADD COLUMN icon VARCHAR(50) NULL;
