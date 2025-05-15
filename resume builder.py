import streamlit as st
from PIL import Image
import os

st.set_page_config(page_title="Resume Maker", layout="wide")

# --- Sidebar for Inputs ---
st.sidebar.title("Resume Details")

# Logo upload
default_logo_path = "assets/bmu_logo.png"
logo = st.sidebar.file_uploader("Upload Logo", type=["png", "jpg", "jpeg"])

# Personal Info
name = st.sidebar.text_input("Full Name", "Harshit Soni")
title = st.sidebar.text_input("Title", "Aspiring software developer...")
email = st.sidebar.text_input("Email", "mailtoh.soni@gmail.com")
phone = st.sidebar.text_input("Phone", "8090935091")
linkedin = st.sidebar.text_input("LinkedIn", "Felix-au")
github = st.sidebar.text_input("GitHub", "Harshit Soni")

# Experience
st.sidebar.subheader("Experience")
exp_company = st.sidebar.text_input("Company", "BLUESTOCK FINTECH")
exp_role = st.sidebar.text_input("Role", "Software Developer Intern")
exp_period = st.sidebar.text_input("Period", "Feb 2025 – Mar 2025 | Remote")
exp_details = st.sidebar.text_area("Details", "Collaborated in a team to develop an IPO Web App...")

# Projects
st.sidebar.subheader("Projects")
proj1_title = st.sidebar.text_input("Project 1 Title", "ADVANCED STOCK PREDICTION")
proj1_desc = st.sidebar.text_area("Project 1 Description", "Developed a hybrid model combining Support Vector Regression...")
proj2_title = st.sidebar.text_input("Project 2 Title", "CONTENT STREAMING PLATFORM")
proj2_desc = st.sidebar.text_area("Project 2 Description", "Built a full-stack platform using MongoDB Atlas, Express, HTML, and CSS...")

# Skills
skills = st.sidebar.text_area("Skills", "C++, Python, CSS, HTML, JavaScript, SQL, Java, REST APIs, Shell, JWT, Axios")

# Education
education = st.sidebar.text_area("Education", "BML Munjal University, B.Tech in Computer Science and Engineering, Aug 2023 - Present, GPA: 7.82/10")

# --- Main Resume Layout ---
col1, col2 = st.columns([2, 1])

with col1:
    # Logo and Name
    cols = st.columns([1, 5])
    with cols[0]:
        if logo:
            st.image(logo, width=80)
        elif os.path.exists(default_logo_path):
            st.image(default_logo_path, width=80)
        else:
            st.write(":blue_book:")
    with cols[1]:
        st.markdown(f"""
        <h1 style='color:#3B82F6; display:inline'>{name.split()[0]}</h1> <h1 style='display:inline'>{' '.join(name.split()[1:])}</h1>
        <div style='font-size:16px; margin-top:-10px'>{title}</div>
        <div style='font-size:14px; color:gray'>
            <span>📧 {email}</span> | <span>📞 {phone}</span> | <span>🔗 {linkedin}</span> | <span>💻 {github}</span>
        </div>
        """, unsafe_allow_html=True)

    st.markdown("---")
    st.markdown(f"""
    <h2 style='color:#2563EB'>Experience</h2>
    <b>{exp_company}</b> | {exp_role} <br>
    <i>{exp_period}</i>
    <ul><li>{exp_details}</li></ul>
    """, unsafe_allow_html=True)

    st.markdown(f"""
    <h2 style='color:#2563EB'>Projects</h2>
    <b>{proj1_title}</b><br>
    <ul><li>{proj1_desc}</li></ul>
    <b>{proj2_title}</b><br>
    <ul><li>{proj2_desc}</li></ul>
    """, unsafe_allow_html=True)

with col2:
    st.markdown(f"""
    <h2 style='color:#2563EB'>Skills</h2>
    <div style='font-size:15px'>{skills}</div>
    <h2 style='color:#2563EB'>Education</h2>
    <div style='font-size:15px'>{education}</div>
    """, unsafe_allow_html=True)

st.markdown("<br><hr><center>Made with Streamlit</center>", unsafe_allow_html=True) 