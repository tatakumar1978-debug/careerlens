const roleSelect = document.getElementById('role');
const roleReference = document.getElementById('roleReference');
const roleSkillsList = document.getElementById('roleSkillsList');
const form = document.getElementById('analyzeForm');
const submitBtn = document.getElementById('submitBtn');

const reportEmpty = document.getElementById('reportEmpty');
const reportContent = document.getElementById('reportContent');
const reportError = document.getElementById('reportError');

let roles = [];

async function loadRoles() {
  try {
    const res = await fetch('/api/roles');
    if (!res.ok) throw new Error('Failed to load roles');
    roles = await res.json();

    roleSelect.innerHTML = '<option value="" disabled selected>Choose a role&hellip;</option>';
    roles.forEach(role => {
      const opt = document.createElement('option');
      opt.value = role.roleId;
      opt.textContent = `${role.roleName} (${role.roleCategory})`;
      roleSelect.appendChild(opt);
    });
  } catch (err) {
    roleSelect.innerHTML = '<option value="" disabled selected>Could not load roles - is the backend running?</option>';
  }
}

roleSelect.addEventListener('change', () => {
  const role = roles.find(r => String(r.roleId) === roleSelect.value);
  if (!role) return;
  roleSkillsList.innerHTML = '';
  role.requiredSkills.forEach(skill => {
    const li = document.createElement('li');
    li.textContent = skill;
    roleSkillsList.appendChild(li);
  });
  roleReference.hidden = false;
});

function renderChips(container, items) {
  container.innerHTML = '';
  if (items.length === 0) {
    const li = document.createElement('li');
    li.textContent = 'None';
    container.appendChild(li);
    return;
  }
  items.forEach(item => {
    const li = document.createElement('li');
    li.textContent = item;
    container.appendChild(li);
  });
}

// makes the number count up from 0 to the final score
function animateNumber(element, target) {
  const finalValue = Math.round(target * 10) / 10;
  const duration = 1200;
  const start = performance.now();

  function step(now) {
    const progress = Math.min((now - start) / duration, 1);
    const eased = 1 - Math.pow(1 - progress, 3);
    element.textContent = Math.round(finalValue * eased * 10) / 10;
    if (progress < 1) {
      requestAnimationFrame(step);
    } else {
      element.textContent = finalValue;
    }
  }
  requestAnimationFrame(step);
}

form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const name = document.getElementById('name').value.trim();
  const email = document.getElementById('email').value.trim();
  const roleId = roleSelect.value;
  const skills = document.getElementById('skills').value
    .split(',')
    .map(s => s.trim())
    .filter(Boolean);

  if (!name || !roleId || skills.length === 0) return;

  submitBtn.disabled = true;
  submitBtn.textContent = 'Analyzing\u2026';
  reportError.hidden = true;

  try {
    const res = await fetch('/api/analyze', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name, email, skills, roleId: Number(roleId) })
    });

    if (!res.ok) {
      const body = await res.text();
      throw new Error(body || 'Analysis failed');
    }

    const data = await res.json();

    document.getElementById('reportCategory').textContent = data.roleCategory;
    document.getElementById('reportRole').textContent = data.roleName;
    document.getElementById('reportName').textContent = data.candidateName;
    renderChips(document.getElementById('matchedList'), data.matchedSkills);
    renderChips(document.getElementById('missingList'), data.missingSkills);
    document.getElementById('terminalText').textContent = data.reportText;

    // start the bar and number at 0, then animate them
    const bar = document.getElementById('scoreBarFill');
    bar.style.width = '0%';
    document.getElementById('scoreValue').textContent = '0';

    reportEmpty.hidden = true;
    reportContent.hidden = false;

    setTimeout(() => {
      bar.style.width = data.matchPercentage + '%';
      animateNumber(document.getElementById('scoreValue'), data.matchPercentage);
    }, 150);
  } catch (err) {
    reportEmpty.hidden = true;
    reportContent.hidden = true;
    reportError.hidden = false;
    reportError.textContent = 'Could not generate report: ' + err.message;
  } finally {
    submitBtn.disabled = false;
    submitBtn.textContent = 'Generate report';
  }
});

// ===== Resume upload: read skills from a PDF / TXT file =====
const resumeFile = document.getElementById('resumeFile');
const uploadBtn = document.getElementById('uploadBtn');
const uploadStatus = document.getElementById('uploadStatus');

uploadBtn.addEventListener('click', () => resumeFile.click());

resumeFile.addEventListener('change', async () => {
  const file = resumeFile.files[0];
  if (!file) return;

  uploadStatus.className = 'upload-status';
  uploadStatus.textContent = 'Reading ' + file.name + '\u2026';

  const formData = new FormData();
  formData.append('file', file);

  try {
    const res = await fetch('/api/extract-skills', { method: 'POST', body: formData });
    const data = await res.json().catch(() => ({}));

    if (!res.ok) throw new Error(data.message || 'Upload failed');

    if (data.skills.length === 0) {
      uploadStatus.className = 'upload-status err';
      uploadStatus.textContent = 'No known skills found in this file.';
      return;
    }

    document.getElementById('skills').value = data.skills.join(', ');
    uploadStatus.className = 'upload-status ok';
    uploadStatus.textContent = '\u2713 Found ' + data.skills.length + ' skills in ' + file.name;
  } catch (err) {
    uploadStatus.className = 'upload-status err';
    uploadStatus.textContent = err.message;
  } finally {
    resumeFile.value = '';
  }
});

loadRoles();