-- userDetails service
CREATE TABLE IF NOT EXISTS user_details (username VARCHAR(45) NOT NULL, password VARCHAR(70) NOT NULL, enabled INT NOT NULL, PRIMARY KEY (username));

-- authorization grants table
CREATE TABLE IF NOT EXISTS user_roles (username VARCHAR(45) NOT NULL, role VARCHAR(15) NOT NULL, PRIMARY KEY (username));

-- client details table
CREATE TABLE IF NOT EXISTS client_details (client_id VARCHAR(20) NOT NULL, secret VARCHAR(70) NOT NULL, scope VARCHAR(10), PRIMARY KEY (client_id));

-- client grant type table
CREATE TABLE IF NOT EXISTS client_grant_type (id VARCHAR(40), client_id VARCHAR(20) NOT NULL, grant_type VARCHAR(20) NOT NULL, PRIMARY KEY (id));

-- client resources table
CREATE TABLE IF NOT EXISTS client_resources (id VARCHAR(40), client_id VARCHAR(20) NOT NULL, resource_id VARCHAR(30) NOT NULL, PRIMARY KEY (id));

-- insert sample values for user credentials
--INSERT INTO user_details ("<usernmae>", "<password>", 1);
--INSERT INTO user_roles ("<username>", "ROLE_ADMIN");