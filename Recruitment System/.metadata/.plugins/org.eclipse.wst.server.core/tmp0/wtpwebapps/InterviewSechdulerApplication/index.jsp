<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/9dc55b3f56.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./jsp/index.css">
    <title>Home</title>
</head>
<body>
   
    
    <main>
        <a href="skills.jsp" style="color:red">
        
                <i class="fa-brands fa-slack"></i>
                Add Skills
            
        </a>
        <a href="/InterviewSechdulerApplication/applicant?action=load" style="color:green">
        
            <i class="fa-solid fa-user"></i>
               Applicants
            
        </a>
        <a href="/InterviewSechdulerApplication/interviewer?action=load" style="color:blue">
        
                <i class="fa-solid fa-user-tie"></i>
                Interviewers
            
        </a>
        <a href="interviews.jsp" style="color:orange">
        
                <i class="fa-solid fa-calendar-days"></i>
            <span style="margin-left:20px">Interviews Scheduled</span>    
            
        </a>
        
    </main>
</body>
</html>