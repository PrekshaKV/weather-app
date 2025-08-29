const form = document.getElementById('searchForm');
const input = document.getElementById('cityInput');
const statusEl = document.getElementById('status');
const resultEl = document.getElementById('result');

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  const city = input.value.trim();
  if (!city) return;

  resultEl.classList.add('hidden');
  statusEl.textContent = 'Fetching weather...';

  try {
    const res = await fetch(`/api/weather?city=${encodeURIComponent(city)}`);
    const text = await res.text();
    if (!res.ok) {
      throw new Error(text || 'Request failed');
    }
    const data = JSON.parse(text);
    renderResult(data);
    statusEl.textContent = '';
  } catch (err) {
    statusEl.textContent = err.message;
  }
});

function renderResult(d) {
  const icon = d.iconUrl ? `<img src="${d.iconUrl}" alt="icon">` : '';
  resultEl.innerHTML = `
    <div class="top">
      ${icon}
      <div>
        <div class="city">${escapeHtml(d.city || '')}</div>
        <div class="desc">${escapeHtml(d.description || '')}</div>
      </div>
    </div>

    <div class="grid">
      <div class="item">
        <b>Temperature</b>
        <div class="big">${formatNumber(d.tempC)} °C</div>
      </div>
      <div class="item">
        <b>Humidity</b>
        <div class="big">${d.humidity}%</div>
      </div>
      <div class="item">
        <b>Wind Speed</b>
        <div class="big">${formatNumber(d.windMps)} m/s</div>
      </div>
    </div>
  `;
  resultEl.classList.remove('hidden');
}

function formatNumber(n) {
  return typeof n === 'number' ? n.toFixed(1) : n;
}
function escapeHtml(s) {
  return String(s).replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
}
