# api-search
This Project is for Searching GitHub and GitLab based on provided user

# How Tos
- Download or Clone project in any director of your choice
- do mvn clean install inside cloned/downloadded "api-search" projecct
- Open in any editor of your choice
- To update Port Number for project - Go to src/main/resource/application.properties file
- User Postman to run the project

# Exposed URL : 
http://localhost:8181/reposearch/{userName}/projects
http://localhost:8181/reposearch/{userName}/projects?project_owner={ProjectOwner}

- PathVariable 		- userName 		- define the owner of GitHut or GitLib
- RequestParameter 	- ProjectOwner 	- defines the owner of project in GitHut or GitLib

# Supported Operations : GET