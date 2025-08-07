// Handle form submission with AJAX
document.querySelector('button[type="submit"]').addEventListener('click', function(e) {
    e.preventDefault();

    const formData = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value
    };

    console.log('Sending data:', formData); // Debug log

    // AJAX request to backend
    fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    })
        .then(async response => {
            console.log('Response status:', response.status); // Debug log

            if (!response.ok) {
                // Try to get error message from response
                let errorMessage = 'Unknown error';
                try {
                    const errorData = await response.text();
                    console.log('Error response:', errorData);
                    errorMessage = errorData;
                } catch (e) {
                    console.log('Could not parse error response');
                }
                throw new Error(`HTTP error! status: ${response.status}, message: ${errorMessage}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            console.log('Backend response structure:', JSON.stringify(data, null, 2)); // Debug: show exact response structure

            // Store user data or token if needed
            if (data.token) {
                localStorage.setItem('authToken', data.token);
                console.log('Token stored:', data.token);
            }

            let userRole = null;

            // Get role from backend response
            if (data.user && data.user.role) {
                userRole = data.user.role;
                localStorage.setItem('userData', JSON.stringify(data.user));
                console.log('Role from data.user.role:', userRole);
            } else if (data.role) {
                userRole = data.role;
                localStorage.setItem('userData', JSON.stringify({
                    username: formData.username,
                    role: data.role
                }));
                console.log('Role from data.role:', userRole);
            } else {
                console.log('❌ No role found in backend response - redirecting to default dashboard');
                alert(`Sign in successful!\nWelcome back, ${formData.username}!`);
                window.location.href = 'dashboard.html';
                return;
            }

            alert(`Sign in successful!\nWelcome back, ${formData.username}!`);

            // DIRECT ROLE-BASED REDIRECTION
            console.log('Redirecting based on role:', userRole);
            if (userRole === 'ADMIN') {
                console.log('✅ ADMIN detected - Redirecting to admin-dashboard.html');
                window.location.href = 'admin-dashboard.html';
            } else if (userRole === 'USER') {
                console.log('✅ USER detected - Redirecting to user-dashboard.html');
                window.location.href = 'user-dashboard.html';
            } else {
                console.log('❌ Unknown role detected - Redirecting to dashboard.html, role was:', userRole);
                window.location.href = 'dashboard.html';
            }
        })
        .catch(error => {
            console.error('Error:', error);

            // Show appropriate error message
            if (error.message.includes('401')) {
                alert('Invalid username or password. Please try again.');
            } else if (error.message.includes('404')) {
                alert('User not found. Please check your username.');
            } else {
                alert(`Sign in failed: ${error.message}`);
            }
        });
});

// Function to make authenticated API requests
function makeAuthenticatedRequest(url, options = {}) {
    const token = localStorage.getItem('authToken');

    if (!token) {
        console.error('No auth token found. Redirecting to login.');
        window.location.href = 'signin.html';
        return Promise.reject('No auth token');
    }

    const defaultOptions = {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    };

    // Merge provided options with defaults
    const finalOptions = {
        ...defaultOptions,
        ...options,
        headers: {
            ...defaultOptions.headers,
            ...options.headers
        }
    };

    return fetch(url, finalOptions)
        .then(response => {
            if (response.status === 401) {
                // Token expired or invalid
                localStorage.removeItem('authToken');
                localStorage.removeItem('userData');
                alert('Session expired. Please sign in again.');
                window.location.href = 'signin.html';
                throw new Error('Unauthorized');
            }
            return response;
        });
}

// Function to check if user is authenticated
function isAuthenticated() {
    return localStorage.getItem('authToken') !== null;
}

// Function to get current user data
function getCurrentUser() {
    const userData = localStorage.getItem('userData');
    return userData ? JSON.parse(userData) : null;
}

// Function to logout
function logout() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('userData');
    window.location.href = 'signin.html';
}

// Example usage of authenticated request:
// makeAuthenticatedRequest('http://localhost:8080/api/protected-endpoint')
//     .then(response => response.json())
//     .then(data => console.log(data))
//     .catch(error => console.error('Error:', error));

// Handle Enter key press in form fields
document.getElementById('username').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        document.querySelector('button[type="submit"]').click();
    }
});

document.getElementById('password').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        document.querySelector('button[type="submit"]').click();
    }
});

// Form validation
function validateForm() {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();

    if (!username) {
        alert('Please enter your username.');
        document.getElementById('username').focus();
        return false;
    }

    if (!password) {
        alert('Please enter your password.');
        document.getElementById('password').focus();
        return false;
    }

    return true;
}

// Add validation before submit
document.querySelector('button[type="submit"]').addEventListener('click', function(e) {
    if (!validateForm()) {
        e.preventDefault();
        return;
    }
});