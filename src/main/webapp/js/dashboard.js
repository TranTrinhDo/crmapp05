$(document).ready(function () {
    $(".menu-dashboard").click(function () {
        window.location.href = 'http://localhost:8080/crmapp05/dashboard'
    })
    $(".menu-user").click(function () {
        window.location.href = 'http://localhost:8080/crmapp05/users'
    })
    $(".menu-role").click(function () {
        window.location.href = 'http://localhost:8080/crmapp05/roles'
    })
    $(".menu-project").click(function () {
        window.location.href = 'http://localhost:8080/crmapp05/projects'
    })
    $(".menu-task").click(function () {
        window.location.href = 'http://localhost:8080/crmapp05/tasks'
    })
    $(".menu-blank").click(function () {
        window.location.href = 'http://localhost:8080/crmapp05/blank'
    })
    $(".menu-error").click(function () {
        window.location.href = 'http://localhost:8080/crmapp05/error'
    })
})