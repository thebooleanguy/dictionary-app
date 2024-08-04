document.addEventListener('DOMContentLoaded', () => {
    const historyMenuButton = document.getElementById('history-menu-button');
    const historyMenu = document.getElementById('history-menu');
    const historyList = document.getElementById('history-list');

    let menuTimeout;

    // Fetch the query history from the server
    function fetchQueryHistory() {
        fetch('/queryHistory')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                historyList.innerHTML = ''; // Clear the current history list

                data.forEach(entry => {
                    const listItem = document.createElement('li');
                    listItem.textContent = entry;
                    listItem.addEventListener('click', () => {
                        const url = `/cachedResult/${encodeURIComponent(entry)}`;
                        console.log('Redirecting to:', url); // Debugging line
                        window.location.href = url;
                    });
                    historyList.appendChild(listItem);
                });
            })
            .catch(error => console.error('Error fetching query history:', error));
    }

    // Show history menu
    function showHistoryMenu() {
        clearTimeout(menuTimeout);
        historyMenu.style.display = 'block';
    }

    // Hide history menu
    function hideHistoryMenu() {
        menuTimeout = setTimeout(() => {
            historyMenu.style.display = 'none';
        }, 200); // Delay before hiding to avoid flicker
    }

    // Display or hide the history menu on button click
    if (historyMenuButton && historyMenu) {
        historyMenuButton.addEventListener('click', (event) => {
            event.stopPropagation(); // Prevent click from closing the menu
            showHistoryMenu();
        });

        // Handle mouse enter and leave events for the history menu and button
        historyMenuButton.addEventListener('mouseenter', showHistoryMenu);
        historyMenu.addEventListener('mouseenter', showHistoryMenu);

        historyMenuButton.addEventListener('mouseleave', hideHistoryMenu);
        historyMenu.addEventListener('mouseleave', hideHistoryMenu);

        // Close menu if clicked outside
        document.addEventListener('click', (event) => {
            if (!historyMenu.contains(event.target) && !historyMenuButton.contains(event.target)) {
                hideHistoryMenu();
            }
        });

        // Fetch query history on page load
        fetchQueryHistory();
    }

    // Only add this part if searchForm exists
    const searchForm = document.querySelector('form');
    if (searchForm) {
        const searchInput = searchForm.querySelector('input[name="prefix"]');

        // Intercept form submission to check LRU cache first
        searchForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const query = searchInput.value;

            // Check if the query is in the cache
            fetch(`/checkCache?query=${encodeURIComponent(query)}`)
                .then(response => response.json())
                .then(data => {
                    if (data.found) {
                        // Redirect to cached result if found
                        const url = `/cachedResult/${encodeURIComponent(query)}`;
                        console.log('Redirecting to:', url); // Debugging line
                        window.location.href = url;
                    } else {
                        // Submit the form normally if not found
                        searchForm.submit();
                    }
                })
                .catch(error => {
                    console.error('Error checking cache:', error);
                    // Submit the form normally in case of an error
                    searchForm.submit();
                });
        });
    }
});
