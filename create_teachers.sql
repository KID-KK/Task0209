USE task;
CREATE TABLE teachers
(t_id INT NOT NULL AUTO_INCREMENT,
 t_name CHAR(50) NOT NULL,
 t_gender CHAR(10) NOT NULL,
 t_age INT NOT NULL,
 PRIMARY KEY (t_id)
 )ENGINE=INNODB;
 INSERT INTO teachers(t_name,
                      t_gender,
                      t_age)
               VALUES('Alice','female',33),
                     ('Bob','male',35),
                      ('Tom','male',30);
                 
                      
ALTER TABLE task.students
ADD CONSTRAINT fk_students_teachers
FOREIGN KEY(stu_teacherid)REFERENCES teachers(t_id);

