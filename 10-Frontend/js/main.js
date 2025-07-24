$(document).ready(function () {
    loadJobs();
    loadPaginatedJobs(0); // load first page by default

    // Live search
    $("#searchInput").on("input", function () {
        const keyword = $(this).val().trim();

        if (keyword.length === 0) {
            loadJobs();
            return;
        }

        $.ajax({
            url: `http://localhost:8080/api/v1/job/search/${encodeURIComponent(keyword)}`,
            method: "GET",
            success: function (response) {
                console.log('Search response:', response);
                // Extract data from APIResponse wrapper - check both 'code' and 'status'
                if (response.code === 200 || response.status === 200) {
                    renderJobs(response.data || []);
                } else {
                    console.error('Search API Error:', response.message);
                    renderJobs([]);
                }
            },
            error: function (xhr, status, error) {
                console.error("Search request failed:", error);
                renderJobs([]);
            }
        });
    });

    function loadJobs() {
        $.ajax({
            url: 'http://localhost:8080/api/v1/job/getAll',
            type: 'GET',
            success: function (response) {
                console.log('Load jobs response:', response);
                // Extract data from APIResponse wrapper - check both 'code' and 'status'
                if (response.code === 200 || response.status === 200) {
                    renderJobs(response.data || []);
                } else {
                    console.error('API Error:', response.message);
                    renderJobs([]);
                }
            },
            error: function (xhr, status, error) {
                console.error('Failed to fetch jobs:', error);
                alert('Failed to fetch jobs');
                renderJobs([]);
            }
        });
    }

    // Make renderJobs globally accessible for pagination
    window.renderJobs = renderJobs;

    function renderJobs(jobs) {
        console.log('Rendering jobs:', jobs, 'Type:', typeof jobs, 'Is Array:', Array.isArray(jobs));

        $('#jobsTableBody').empty();

        // Safety check
        if (!jobs || !Array.isArray(jobs)) {
            console.error('renderJobs expects an array, got:', typeof jobs, jobs);
            $('#jobsTableBody').append(`<tr><td colspan="7" class="text-center">Error loading jobs</td></tr>`);
            return;
        }

        if (jobs.length === 0) {
            $('#jobsTableBody').append(`<tr><td colspan="7" class="text-center">No jobs found</td></tr>`);
            return;
        }

        jobs.forEach((job, index) => {
            $('#jobsTableBody').append(`
                <tr>
                    <td>${index + 1}</td>
                    <td>${job.jobTitle || 'N/A'}</td>
                    <td>${job.company || 'N/A'}</td>
                    <td>${job.location || 'N/A'}</td>
                    <td>${job.type || 'N/A'}</td>
                    <td>${job.status || 'Open'}</td>
                    <td>
                        <button class="btn btn-sm btn-warning edit-btn" data-id="${job.id}">Edit</button>
                        <button class="btn btn-sm btn-danger delete-btn" data-id="${job.id}">Delete</button>
                    </td>
                </tr>
            `);
        });
    }

    // Add new job
    $('#saveJobBtn').click(function () {
        const jobData = {
            jobTitle: $('#jobTitle').val(),
            company: $('#companyName').val(),
            location: $('#jobLocation').val(),
            type: $('#jobType').val(),
            jobDescription: $('#jobDescription').val()
        };

        $.ajax({
            url: 'http://localhost:8080/api/v1/job/create',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(jobData),
            success: function (response) {
                console.log('Create job response:', response);
                if (response.code === 200 || response.status === 200) {
                    loadJobs();
                    $('#addJobForm')[0].reset();
                    alert('Job created successfully!');
                } else {
                    alert('Error: ' + response.message);
                }
            },
            error: function (xhr, status, error) {
                console.error('Failed to add job:', error);
                alert('Failed to add job');
            }
        });
    });

    // Show job in modal
    $(document).on('click', '.edit-btn', function () {
        const jobId = $(this).data('id');

        // Fetch all jobs and find by ID
        $.ajax({
            url: 'http://localhost:8080/api/v1/job/getAll',
            type: 'GET',
            success: function (response) {
                console.log('Edit - fetch jobs response:', response);

                if (response.code === 200 || response.status === 200) {
                    const jobs = response.data || [];
                    const job = jobs.find(j => j.id === jobId);

                    if (!job) {
                        alert("Job not found");
                        return;
                    }

                    $('#editJobId').val(job.id);
                    $('#editJobTitle').val(job.jobTitle);
                    $('#editCompanyName').val(job.company);
                    $('#editJobLocation').val(job.location);
                    $('#editJobType').val(job.type);
                    $('#editJobDescription').val(job.jobDescription);
                    $('#editJobModal').modal('show');
                } else {
                    alert('Error fetching jobs: ' + response.message);
                }
            },
            error: function (xhr, status, error) {
                console.error('Failed to fetch job details:', error);
                alert('Failed to fetch job details');
            }
        });
    });

    // Update job
    $('#updateJobBtn').click(function () {
        const updatedJob = {
            id: $('#editJobId').val(),
            jobTitle: $('#editJobTitle').val(),
            company: $('#editCompanyName').val(),
            location: $('#editJobLocation').val(),
            type: $('#editJobType').val(),
            jobDescription: $('#editJobDescription').val()
        };

        $.ajax({
            url: 'http://localhost:8080/api/v1/job/update',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updatedJob),
            success: function (response) {
                console.log('Update job response:', response);
                if (response.code === 200 || response.status === 200) {
                    loadJobs();
                    $('#editJobForm')[0].reset();
                    $('#editJobModal').modal('hide');
                    alert('Job updated successfully!');
                } else {
                    alert('Error: ' + response.message);
                }
            },
            error: function (xhr, status, error) {
                console.error('Failed to update job:', error);
                alert('Failed to update job');
            }
        });
    });

    // Delete job (soft delete with status change)
    $(document).on('click', '.delete-btn', function () {
        const jobId = $(this).data('id');

        if (confirm("You sure you wanna delete this job?")) {
            $.ajax({
                url: `http://localhost:8080/api/v1/job/status/${jobId}`,
                type: 'PATCH',
                success: function (response) {
                    console.log('Delete job response:', response);
                    if (response.code === 200) {
                        loadJobs();
                        alert('Job status changed successfully!');
                    } else {
                        alert('Error: ' + response.message);
                    }
                },
                error: function (xhr, status, error) {
                    console.error('Failed to delete job:', error);
                    alert('Couldn\'t delete that job. Try again');
                }
            });
        }
    });
});

// Pagination functionality
let currentPage = 0;
let pageSize = 5;

function loadPaginatedJobs(page) {
    $.ajax({
        url: `http://localhost:8080/api/v1/job/paginated?page=${page}&size=${pageSize}`,
        type: 'GET',
        success: function (response) {
            console.log('Paginated jobs response:', response);
            console.log('Response type:', typeof response);
            console.log('Response keys:', Object.keys(response));

            // Handle different possible response formats
            let pageData;

            if ((response.code === 200 || response.status === 200) && response.data) {
                // APIResponse wrapper format
                pageData = response.data;
                console.log('Using APIResponse format, pageData:', pageData);
            } else if (response.content !== undefined) {
                // Direct Page object format (like your current response)
                pageData = response;
                console.log('Using direct Page format, pageData:', pageData);
            } else {
                console.error('Unexpected pagination response format:', response);
                alert('Error: Unexpected response format for pagination');
                return;
            }

            // Verify pageData has required properties
            if (!pageData || typeof pageData !== 'object') {
                console.error('Invalid pageData:', pageData);
                alert('Error: Invalid pagination data received');
                return;
            }

            const jobs = pageData.content || [];
            console.log('Jobs from pagination:', jobs);

            // Call renderJobs function from the global scope
            window.renderJobs(jobs); // render table rows
            renderPagination(pageData);   // build pagination
            currentPage = page;
        },
        error: function (xhr, status, error) {
            console.error('Failed to load paginated jobs:', error);
            console.error('XHR status:', xhr.status);
            console.error('Response text:', xhr.responseText);
            alert("Failed to load paginated jobs");
        }
    });
}

function renderPagination(pageData) {
    const totalPages = pageData.totalPages;
    const currentPageNum = pageData.number;

    let paginationHtml = '';

    if (totalPages > 1) {
        // Prev button
        paginationHtml += `
            <li class="page-item ${currentPageNum === 0 ? 'disabled' : ''}">
                <a class="page-link" href="#" data-page="${currentPageNum - 1}">Prev</a>
            </li>
        `;

        // Page numbers
        for (let i = 0; i < totalPages; i++) {
            paginationHtml += `
                <li class="page-item ${i === currentPageNum ? 'active' : ''}">
                    <a class="page-link" href="#" data-page="${i}">${i + 1}</a>
                </li>
            `;
        }

        // Next button
        paginationHtml += `
            <li class="page-item ${currentPageNum === totalPages - 1 ? 'disabled' : ''}">
                <a class="page-link" href="#" data-page="${currentPageNum + 1}">Next</a>
            </li>
        `;
    }

    $("#pagination").html(paginationHtml);
}

$(document).on("click", ".page-link", function (e) {
    e.preventDefault();
    const page = $(this).data("page");
    if (page >= 0) {
        loadPaginatedJobs(page);
    }
});