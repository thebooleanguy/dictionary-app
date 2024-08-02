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

    // Submit form traditionally, as search history is handled by the backend
    document.querySelector('form').addEventListener('submit', (event) => {
        // Form will be submitted traditionally
    });
});
