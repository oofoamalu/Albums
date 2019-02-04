# Albums
This is a one-page application which displays a list of album titles on the screen.

Future improvements

1. Make use of pagination when populating data in the list view using the android paging library. This way, we don’t load all the data at once, but instead load only the necessary data to populate the current visible page. Then as the user scrolls down, we load the data for the next page.

2. What happens when there is an update/new data on the server? With current implementation, there is no way to retrieve this new data. Unless the database is wiped and the application then loads data from the server. How can we improve this? We can implement a worker (using android Work Manager) to sync data after a period of time. Another approach will be to make the api return a hot and infinite observable which will continuously emit new data.

3. Retry on network failure.

