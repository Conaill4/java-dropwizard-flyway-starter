
CREATE TABLE LegacyEmployeeProject
(
    ID            int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    ProjectID     int NOT NULL,
    DeliveryEmpID int NOT NULL

);

create trigger DeleteEmpProject
    after delete
    on WorksOn
    FOR EACH ROW
BEGIN

    INSERT INTO LegacyEmployeeProject (ProjectID, DeliveryEmpID)
    VALUES (OLD.ProjectID, OLD.DeliveryID);

END

insert into SalesEmployee
