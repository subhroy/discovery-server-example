# discovery-server-example
Discovery Server Demo Project
Project to demonstrate Nextflix Eureka Library for creating discovery server and connnect from the Eureka Client using DNS and auto register the REST services.

# Scenario : 
   - 'movie-info-service' and 'movie-ratings-data-service' registering APIs directly to the 'discovery-server(Netflix Eureka Server)'
   - 'movie-catalog-service' is consuming 'movie-info-service' and 'movie-ratings-data-service' using DNS Name

All REST calls for consumptions are written using REST Template, but can be changed to WebClient.
