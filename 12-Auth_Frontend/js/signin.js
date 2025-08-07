document.querySelector('button[type="submit"]').addEventListener('click', function(e) {
    e.preventDefault();

    const formData = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value
    };

    console.log('Sending data:', formData);

    fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    })
        .then(async response => {
            console.log('Response status:', response.status);

            if (!response.ok) {
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

            if (data.token) {
                localStorage.setItem('authToken', data.token);
            }
            if (data.user) {
                localStorage.setItem('userData', JSON.stringify(data.user));

                alert(`Sign in successful!\nWelcome back, ${formData.username}!`);

                if (data.user.role === 'ADMIN') {
                    window.location.href = 'admin-dashboard.html';
                } else if (data.user.role === 'USER') {
                    window.location.href = 'user-dashboard.html';
                } else {
                    window.location.href = 'dashboard.html';
                }
            } else {
                alert(`Sign in successful!\nWelcome back, ${formData.username}!`);
                window.location.href = 'dashboard.html';
            }
        })
        .catch(error => {
            console.error('Error:', error);

            if (error.message.includes('401')) {
                alert('Invalid username or password. Please try again.');
            } else if (error.message.includes('404')) {
                alert('User not found. Please check your username.');
            } else {
                alert(`Sign in failed: ${error.message}`);
            }
        });
});

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

document.querySelector('button[type="submit"]').addEventListener('click', function(e) {
    if (!validateForm()) {
        e.preventDefault();
        return;
    }
});