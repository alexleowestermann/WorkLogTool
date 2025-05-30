const logTableBody = document.querySelector("#logTable tbody");
const addLogForm = document.querySelector("#logForm");

async function fetchLogs() {
  const response = await fetch("/api/logs");
  const logs = await response.json();

  logTableBody.innerHTML = ""; // Clear table

  logs.forEach(log => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td><input type="checkbox" class="logCheckBox"></td>
      <td>${log.title}</td>
      <td>${log.date}</td>
      <td>${log.description}</td>
      <td>${log.tags.join(", ")}</td>
      <td><button class="deleteBtn">Delete</button></td>
    `;
    logTableBody.appendChild(row);
  });
}

fetchLogs();

addLogForm.addEventListener("submit", async (e) => {
  e.preventDefault();

  const title = document.querySelector("#title").value;
  const date = document.querySelector("#date").value;
  const description = document.querySelector("#description").value;
  const tags = document.querySelector("#tags").value.split(",").map(tag => tag.trim());

  const newLog = { title, date, description, tags };

  await fetch("/api/logs", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(newLog)
  });

  fetchLogs();
  addLogForm.reset();
});

// Use event delegation for delete buttons
logTableBody.addEventListener("click", (e) => {
  if (e.target.classList.contains("deleteBtn")) {
    const row = e.target.closest("tr");
    const title = row.children[1].textContent;

    fetch(`/api/logs/${title}`, { method: "DELETE" })
      .then(() => {
        row.remove();
      })
      .catch(err => console.error("Error deleting log:", err));
  }
});