document.querySelector('form').addEventListener('submit', function(e) {
    e.preventDefault();

    const formData = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        role: document.querySelector('input[name="accountType"]:checked').value.toUpperCase()
    };

    console.log('Sending data:', formData);

    fetch('http://localhost:8080/auth/register', {
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
            alert(`Account created successfully!\nRole: ${formData.role}\nWelcome, ${formData.username}!`);
            window.location.href = 'dashboard.html';
        })
        .catch(error => {
            console.error('Error:', error);
            alert(`Error creating account: ${error.message}`);
        });
});

document.querySelectorAll('input[name="accountType"]').forEach(radio => {
    radio.addEventListener('change', function() {
        document.querySelectorAll('.role-option').forEach(option => {
            option.style.background = '';
        });
        this.closest('.role-option').style.background = 'rgba(126, 107, 255, 0.2)';
    });
});

document.querySelector('input[name="accountType"]:checked').closest('.role-option').style.background = 'rgba(126, 107, 255, 0.2)';