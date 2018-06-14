--DROP TABLE APPROVALS;
--DROP TABLE REIMBURSEMENT_FORM;
--DROP TABLE EMPLOYEE;
--DROP TABLE EVENT;
--DROP TABLE EVENT_TYPE_LOOKUP;
--DROP TABLE POSITION_LOOKUP;

--TRUNCATE TABLE POSITION_LOOKUP;
--INSERT INTO POSITION_LOOKUP VALUES(0, 'EMPLOYEE');
--INSERT INTO POSITION_LOOKUP VALUES(1, 'DIRECT SUPERVISOR');
--INSERT INTO POSITION_LOOKUP VALUES(2, 'DEPARTMENT HEAD');
--INSERT INTO POSITION_LOOKUP VALUES(3, 'BENEFITS COORDINATOR');
--INSERT INTO POSITION_LOOKUP VALUES(4, 'CEO');
--commit;
--ALTER TABLE EVENT_TYPE_LOOKUP DROP COLUMN PERCENT_COVERED;
--ALTER TABLE EVENT_TYPE_LOOKUP MODIFY PERCENT_COVERED FLOAT;
--ALTER TABLE EVENT_TYPE_LOOKUP ADD PERCENT_COVERED FLOAT;
ALTER TABLE APPROVALS ADD SUPERTIME TIMESTAMP;
ALTER TABLE APPROVALS ADD DEPARTMENTTIME TIMESTAMP;
ALTER TABLE APPROVALS ADD BENCOTIME TIMESTAMP;
ALTER TABLE EVENT ADD FORM_ID NUMBER;
--TRUNCATE TABLE EVENT_TYPE_LOOKUP;
--INSERT INTO EVENT_TYPE_LOOKUP VALUES(0, 'CLASS',0.8);
--INSERT INTO EVENT_TYPE_LOOKUP VALUES(1, 'TRAINING',0.6);
--INSERT INTO EVENT_TYPE_LOOKUP VALUES(2, 'CERTIFICATION_PREP',0.8);
--INSERT INTO EVENT_TYPE_LOOKUP VALUES(3, 'CERTIFICATION',1);
--INSERT INTO EVENT_TYPE_LOOKUP VALUES(4, 'TECHNICAL_TRAINING',0.9);
--INSERT INTO EVENT_TYPE_LOOKUP VALUES(5, 'OTHER',0.3);
--UPDATE EVENT_TYPE_LOOKUP SET PERCENT_COVERED = 0.6 WHERE EVENT_TYPE=1;
--COMMIT;

--TRUNCATE TABLE EVENT;
--INSERT INTO EVENT VALUES(0, 0, NULL, NULL);
--COMMIT;

--ALTER TABLE REIMBURSEMENT_FORM DROP COLUMN EVENT_ID;
--ALTER TABLE REIMBURSEMENT_FORM ADD EVENT_TYPE INTEGER;
--ALTER TABLE REIMBURSEMENT_FORM
--ADD CONSTRAINT FK_EVENT_TYPE_R
--FOREIGN KEY (EVENT_TYPE) REFERENCES EVENT_TYPE_LOOKUP(EVENT_TYPE);

--EMPLOYEE TABLE
CREATE TABLE EMPLOYEE (
    EMP_ID INTEGER PRIMARY KEY,
    USERNAME VARCHAR2(100) UNIQUE,
    PASSWORD VARCHAR2(100),
    NAME VARCHAR2(100),
    POSITION_TYPE INTEGER,
    MANAGER_ID INTEGER
);

--POSITION TITLE LOOKUP TABLE
CREATE TABLE POSITION_LOOKUP (
    POSITION_TYPE INTEGER PRIMARY KEY,
    TITLE VARCHAR2(100)
);

--REIMBURSEMENT FORM TABLE
CREATE TABLE REIMBURSEMENT_FORM (
    FORM_ID INTEGER PRIMARY KEY,
    EVENT_ID INTEGER,
    EMP_ID INTEGER,
    TIMEOFEVENT VARCHAR2(100),
    DATEOFEVENT VARCHAR2(100),
    LOCATION VARCHAR2(100),
    DESCRIPTION VARCHAR2(200),
    COST FLOAT,
    PROJECTEDREIMBURSEMENT FLOAT,
    URGENT INTEGER
);

--APPROVALS TABLE
CREATE TABLE APPROVALS (
    FORM_ID INTEGER,
    SUPERVISOR INTEGER,
    DEPARTMENTHEAD INTEGER,
    BENCO INTEGER,
    CEO INTEGER
);

--EVENT TABLE
CREATE TABLE EVENT (
    EVENT_ID INTEGER PRIMARY KEY,
    EVENT_TYPE INTEGER,
    GRADE NUMBER,
    GRADING_FORMAT VARCHAR2(100)
);

--EVENT LOOKUP TABLE
CREATE TABLE EVENT_TYPE_LOOKUP (
    EVENT_TYPE INTEGER PRIMARY KEY,
    TITLE VARCHAR2(100)
);

ALTER TABLE EMPLOYEE
ADD CONSTRAINT FK_POSITION_TYPE
FOREIGN KEY (POSITION_TYPE) REFERENCES POSITION_LOOKUP(POSITION_TYPE);

ALTER TABLE REIMBURSEMENT_FORM
ADD CONSTRAINT FK_EVENT_ID
FOREIGN KEY (EVENT_ID) REFERENCES EVENT(EVENT_ID);

ALTER TABLE REIMBURSEMENT_FORM
ADD CONSTRAINT FK_EMP_ID
FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEE(EMP_ID);

ALTER TABLE APPROVALS
ADD CONSTRAINT FK_FORM_ID
FOREIGN KEY (FORM_ID) REFERENCES REIMBURSEMENT_FORM(FORM_ID);

ALTER TABLE EVENT 
ADD CONSTRAINT FK_EVENT_TYPE
FOREIGN KEY (EVENT_TYPE) REFERENCES EVENT_TYPE_LOOKUP(EVENT_TYPE);

CREATE SEQUENCE EMPLOYEESEQ
MINVALUE 1000
INCREMENT BY 1
CACHE 5;

CREATE SEQUENCE REIMBURSEMENTSEQ
MINVALUE 1000
INCREMENT BY 1
CACHE 5;

CREATE SEQUENCE EVENTSEQ
MINVALUE 1000
INCREMENT BY 1
CACHE 5;
--ALTER TABLE APPROVALS DROP COLUMN SUPERTIME;
--prcedures
---INSERT A REIMBURSEMENT FORM ALSO PUTS THE FORM INTO APPROVALS
EXECUTE CREATE_REIMBURSEMENT_FORM(1,1056,'F','F','F','F',1,1,1,0,0,0)
CREATE OR REPLACE PROCEDURE CREATE_REIMBURSEMENT_FORM(
NEW_EVENT_ID IN NUMBER,
NEW_EMP_ID IN NUMBER,
NEW_TIMEOFEVENT IN VARCHAR2,
NEW_DATEOFEVENT IN VARCHAR2,
NEW_LOCATION IN VARCHAR2,
NEW_DESCRIPTION IN VARCHAR2,
NEW_COST IN FLOAT,
NEW_PROJECTEDREIMBURSEMENT IN FLOAT,
NEW_URGENT IN NUMBER,
SUPERVISOR_APPROVAL IN NUMBER,
DEPARTMENT_APPROVAL IN NUMBER,
BENCO_APPROVAL IN NUMBER
)
AS
    FORM_ID_S NUMBER;
BEGIN
FORM_ID_S := REIMBURSEMENTSEQ.NEXTVAL;
INSERT INTO REIMBURSEMENT_FORM VALUES(
FORM_ID_S,
NEW_EMP_ID,
NEW_TIMEOFEVENT,
NEW_DATEOFEVENT,
NEW_LOCATION,
NEW_DESCRIPTION,
NEW_COST,
NEW_PROJECTEDREIMBURSEMENT,
NEW_URGENT,
NEW_EVENT_ID);
INSERT INTO APPROVALS VALUES(
FORM_ID_S,
SUPERVISOR_APPROVAL,
DEPARTMENT_APPROVAL,
BENCO_APPROVAL,
0,
NULL,
SYSTIMESTAMP,NULL,NULL)
;
INSERT INTO EVENT VALUES(
NEW_EVENT_ID,
NEW_EVENT_ID,
NULL,
'PASS/FAIL',
FORM_ID_S
);
END CREATE_REIMBURSEMENT_FORM;
/
--UPDATE GRADE
CREATE OR REPLACE PROCEDURE PASS_GRADE(
FORMID IN NUMBER
)
AS
BEGIN
UPDATE EVENT
SET GRADE = 1 WHERE EVENT.FORM_ID = FORMID;
END;
/
CREATE OR REPLACE PROCEDURE FAIL_GRADE(
FORMID IN NUMBER
)
AS
BEGIN
UPDATE EVENT
SET GRADE = -1 WHERE EVENT.FORM_ID = FORMID;
END;
/
--get reimburement for by employee it
--UPDATE APPROVALS BY FORM ID
CREATE OR REPLACE PROCEDURE UPDATE_APPROVAL(
SUPERVISOR IN NUMBER,
DEPARTMENT IN NUMBER,
BENCO IN NUMBER,
FORMID IN NUMBER
)
AS
BEGIN
UPDATE APPROVALS
SET APPROVALS.SUPERVISOR = SUPERVISOR,
APPROVALS.DEPARTMENTHEAD = DEPARTMENT,
APPROVALS.BENCO = BENCO
WHERE APPROVALS.FORM_ID = FORMID;
END;
/
--SET SUPERVISOR APPROVAL BY FORM ID
CREATE OR REPLACE PROCEDURE SUPERVISOR_APPROVAL(
FORMID IN NUMBER
)
AS
BEGIN
UPDATE APPROVALS
SET APPROVALS.SUPERVISOR = 1,APPROVALS.DEPARTMENTTIME = SYSTIMESTAMP
WHERE APPROVALS.FORM_ID = FORMID;
END;
/
--SUPERVISOR DENIAL WITH REASON
CREATE OR REPLACE PROCEDURE SUPERVISOR_DENIAL(
FORMID IN NUMBER,
REASON IN VARCHAR2
)
AS
BEGIN
UPDATE APPROVALS
SET APPROVALS.SUPERVISOR = -1,
APPROVALS.DENIAL_REASON = REASON
WHERE APPROVALS.FORM_ID = FORMID;
END;
/
--SET DEPARTMENT APPROVAL BY FORM ID
CREATE OR REPLACE PROCEDURE DEPARTMENT_APPROVAL(
FORMID IN NUMBER
)
AS
BEGIN
UPDATE APPROVALS
SET APPROVALS.DEPARTMENTHEAD = 1,APPROVALS.BENCOTIME = SYSTIMESTAMP
WHERE APPROVALS.FORM_ID = FORMID;
END;
/

CREATE OR REPLACE PROCEDURE DEPARTMENT_DENIAL(
FORMID IN NUMBER,
REASON IN VARCHAR2
)
AS
BEGIN
UPDATE APPROVALS
SET APPROVALS.DEPARTMENTHEAD = -1,
APPROVALS.DENIAL_REASON = REASON
WHERE APPROVALS.FORM_ID = FORMID;
END;
/
--SET BENCO APPROVAL BY FORM ID
CREATE OR REPLACE PROCEDURE BENCO_APPROVAL(
FORMID IN NUMBER
)
AS
BEGIN
UPDATE APPROVALS
SET APPROVALS.BENCO = 1
WHERE APPROVALS.FORM_ID = FORMID;
END;
/
CREATE OR REPLACE PROCEDURE BENCO_DENAIL(
FORMID IN NUMBER,
REASON IN VARCHAR2
)
AS
BEGIN
UPDATE APPROVALS
SET APPROVALS.BENCO = -1,
APPROVALS.DENIAL_REASON = REASON
WHERE APPROVALS.FORM_ID = FORMID;
END;
/
CREATE OR REPLACE PROCEDURE GET_REASON(
FORMID IN NUMBER,
REASON OUT VARCHAR2
)
AS
BEGIN
SELECT APPROVALS.DENIAL_REASON INTO REASON FROM APPROVALS WHERE FORMID = APPROVALS.FORM_ID;
END;
/

--CREATE EMPLOYEE
CREATE OR REPLACE PROCEDURE INSERT_EMPLOYEE (
IN_USERNAME IN VARCHAR2, 
IN_PASSWORD IN VARCHAR2, 
IN_NAME IN VARCHAR2,
IN_POSITION_TYPE IN NUMBER,
IN_MANAGER_ID IN NUMBER
)
AS
BEGIN
INSERT INTO EMPLOYEE VALUES(EMPLOYEESEQ.NEXTVAL, IN_USERNAME, IN_PASSWORD, IN_NAME, IN_POSITION_TYPE, IN_MANAGER_ID);
END;
/
--UPDATE_EMPLOYEE,DELETE_EMPLOYEE,GET_PASSWORD
--updates employee BY USERNAME
CREATE OR REPLACE PROCEDURE UPDATE_EMPLOYEE(
OLD_USERNAME IN VARCHAR2,
NEW_USERNAME IN VARCHAR2,
NEW_PASSWORD IN VARCHAR2,
NEW_NAME IN VARCHAR2,
NEW_POSITION_TYPE IN NUMBER,
NEW_MANAGER_ID IN NUMBER
)
AS
BEGIN
UPDATE EMPLOYEE
SET-- EMP_ID = COALESCE(NEW_EMP_ID,EMP_ID),
USERNAME = COALESCE(NEW_USERNAME,USERNAME),
PASSWORD= COALESCE(NEW_PASSWORD,PASSWORD),
NAME = COALESCE(NEW_NAME,NAME),
POSITION_TYPE = COALESCE(NEW_POSITION_TYPE,POSITION_TYPE),
MANAGER_ID = COALESCE(NEW_MANAGER_ID,MANAGER_ID)
WHERE OLD_USERNAME=USERNAME;
END UPDATE_EMPLOYEE;
/
--deletes employee by username
CREATE OR REPLACE PROCEDURE DELETE_EMPLOYEE(
EMP_USERNAME IN VARCHAR2
)
AS
BEGIN
DELETE FROM EMPLOYEE WHERE EMPLOYEE.USERNAME = EMP_USERNAME;
END DELETE_EMPLOYEE;
/
--retrieves password
CREATE OR REPLACE PROCEDURE GET_PASSWORD(
USER_NAME IN VARCHAR2,
PASS OUT VARCHAR2
)
AS
BEGIN
SELECT PASSWORD INTO PASS FROM EMPLOYEE WHERE USER_NAME = EMPLOYEE.USERNAME;
END GET_PASSWORD;
/
CREATE OR REPLACE PROCEDURE GET_USERNAME_BY_PASSWORD(
USER_NAME IN VARCHAR2,
EMPID OUT NUMBER,
PASS OUT VARCHAR2,
NAME OUT VARCHAR2,
POSITIONTYPE OUT NUMBER,
MANAGERID OUT NUMBER
)
AS
BEGIN
SELECT EMP_ID INTO EMPID FROM EMPLOYEE WHERE USER_NAME = EMPLOYEE.USERNAME;
SELECT PASSWORD INTO PASS FROM EMPLOYEE WHERE USER_NAME = EMPLOYEE.USERNAME;
SELECT EMPLOYEE.NAME INTO NAME FROM EMPLOYEE WHERE USER_NAME = EMPLOYEE.USERNAME;
SELECT POSITION_TYPE INTO POSITIONTYPE FROM EMPLOYEE WHERE USER_NAME = EMPLOYEE.USERNAME;
SELECT MANAGER_ID INTO MANAGERID FROM EMPLOYEE WHERE USER_NAME = EMPLOYEE.USERNAME;
END GET_USERNAME_BY_PASSWORD;
/
----forms prcedures
----testing
CREATE TABLE ATTACHMENT_TEST (
NAME VARCHAR2(100),
DESCRIPTION VARCHAR2(100),
FILE_ BLOB
);
DROP TABLE ATTACHMENT_TEST;
CREATE OR REPLACE PROCEDURE INSERT_ATTACHMENT(
NAMEN IN VARCHAR2,
DESCRIPTION_ IN VARCHAR2,
FILEN IN BLOB
)
AS
BEGIN
INSERT INTO ATTACHMENT_TEST VALUES(NAMEN,DESCRIPTION_,FILEN);
END INSERT_ATTACHMENT;
/
execute ATTACHMENT_TEST('TEST','A PDF FILE',www.pdf995.com/samples/pdf.pdf);
ALTER TABLE APPROVALS ADD DENIAL_REASON VARCHAR2(100);
--get Reimbursement left
CREATE OR REPLACE PROCEDURE REIMBURSEMENT_LEFT(
EMPID IN NUMBER,
TOTAL OUT FLOAT
)
AS
BEGIN
SELECT 1000-SUM(PROJECTEDREIMBURSEMENT) INTO TOTAL FROM REIMBURSEMENT_FORM WHERE EMP_ID = EMPID;
END;
/
SELECT * FROM REIMBURSEMENT_FORM RF INNER JOIN APPROVALS A ON RF.FORM_ID = A.FORM_ID WHERE RF.EMP_ID=1004;
UPDATE APPROVALS
   SET SUPERVISOR = 0;
   CREATE OR REPLACE PROCEDURE AUTO_DEPART
AS
cursor c1 is
     SELECT *
     FROM APPROVALS;
TEMP TIMESTAMP := SYSTIMESTAMP;
SUPTIME TIMESTAMP;
FORMID NUMBER;
BEGIN
SELECT SYSTIMESTAMP INTO TEMP FROM DUAL;
FOR REC IN C1 LOOP
    IF(TEMP - REC.DEPARTMENTTIME>INTERVAL '14' DAY)THEN DEPARTMENT_APPROVAL(REC.FORM_ID);ELSE dbms_output.PUT_line(REC.FORM_ID); END IF;
END LOOP;
END;
/
CREATE OR REPLACE PROCEDURE AUTO_SUPER
AS
cursor c1 is
     SELECT *
     FROM APPROVALS;
TEMP TIMESTAMP := SYSTIMESTAMP;
SUPTIME TIMESTAMP;
FORMID NUMBER;
BEGIN
SELECT SYSTIMESTAMP INTO TEMP FROM DUAL;
FOR REC IN C1 LOOP
    IF(TEMP - REC.SUPERTIME>INTERVAL '14' DAY)THEN SUPERVISOR_APPROVAL(REC.FORM_ID);ELSE dbms_output.PUT_line(REC.FORM_ID); END IF;
END LOOP;
END;
/
EXECUTE AUTO_SUPER;
begin
dbms_scheduler.create_job (
   job_name           =>  'UPDATE_SUPER',
   job_type           =>  'STORED_PROCEDURE',
   job_action         =>  'AUTO_SUPER',
   start_date         =>  SYSTIMESTAMP,
   repeat_interval    =>  'FREQ=DAILY',
   enabled            =>  TRUE);
END;
/
begin
dbms_scheduler.create_job (
   job_name           =>  'UPDATE_DEPARTMENT',
   job_type           =>  'STORED_PROCEDURE',
   job_action         =>  'AUTO_DEPART',
   start_date         =>  SYSTIMESTAMP,
   repeat_interval    =>  'FREQ=DAILY',
   enabled            =>  TRUE);
END;
/
