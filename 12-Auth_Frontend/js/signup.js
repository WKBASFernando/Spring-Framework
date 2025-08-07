// Handle form submission with AJAX
document.querySelector('form').addEventListener('submit', function(e) {
    e.preventDefault();

    const formData = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        role: document.querySelector('input[name="accountType"]:checked').value.toUpperCase() // Convert to ADMIN or USER
    };

    console.log('Sending data:', formData); // Debug log

    // AJAX request to backend
    fetch('http://localhost:8080/auth/register', {
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
            alert(`Account created successfully!\nRole: ${formData.role}\nWelcome, ${formData.username}!`);
            // Redirect to dashboard after successful signup
            window.location.href = 'dashboard.html';
        })
        .catch(error => {
            console.error('Error:', error);
            alert(`Error creating account: ${error.message}`);
        });
});

// Add visual feedback for role selection
document.querySelectorAll('input[name="accountType"]').forEach(radio => {
    radio.addEventListener('change', function() {
        document.querySelectorAll('.role-option').forEach(option => {
            option.style.background = '';
        });
        this.closest('.role-option').style.background = 'rgba(126, 107, 255, 0.2)';
    });
});

// Set initial selection visual
document.querySelector('input[name="accountType"]:checked').closest('.role-option').style.background = 'rgba(126, 107, 255, 0.2)';