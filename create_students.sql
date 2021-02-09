#创建一张表
CREATE DATABASE task;
USE task;
CREATE TABLE students
( stu_id              INT       NOT NULL AUTO_INCREMENT,
  stu_name            CHAR(30)  NOT NULL,
  stu_gender          CHAR(10)   NOT NULL,
  stu_teachername     CHAR(30)  NULL,
  stu_grade           INT       NULL,
  stu_class           INT       NULL,
  PRIMARY KEY(stu_id)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;
#插入数据
INSERT INTO students(stu_name,
                     stu_gender,
                     stu_teachername,
                     stu_grade,
                     stu_class)
              VALUES('Aa','male','Alice',7,1),
                    ('Bb','female','Bob',9,2),
                    ('Cc','male','Alice',8,1),
                    ('Dd','male','Tom',7,3),
                    ('Ee','female','Alice',9,3),
                    ('Ff','male','Tom',8,1),
                    ('Gg','female','Bob',8,3),
                    ('Hh','female','Bob',8,2);
#查询老师Alice的学生
SELECT stu_id,stu_name,stu_gender,stu_grade,stu_class
FROM students
WHERE stu_teachername='Alice'
ORDER BY stu_id ;
#删除stu_id=5的学生
DELETE FROM students
WHERE stu_id=5;
#更改stu_id=7的学生姓名为Rr
UPDATE students
SET stu_name='Rr'
WHERE stu_id=7;
#添加一列final_score期末成绩
ALTER TABLE students
ADD final_score INT NULL;
#给final_score加入数据,NULL表示缺考
UPDATE students
SET final_score=91 WHERE stu_id=1;
UPDATE students
SET final_score=87 WHERE stu_id=2;
UPDATE students
SET final_score=93 WHERE stu_id=3;
UPDATE students
SET final_score=81 WHERE stu_id=6;
UPDATE students
SET final_score=97 WHERE stu_id=8;