// ==========================================================
// Connect with Ankit — home page interactions
// 1) day / night theme toggle
// 2) subtle 3D tilt on the profile card (cursor follow)
// ==========================================================

(function () {
  var root = document.documentElement;
  var toggleBtn = document.getElementById('themeToggle');
  var reduceMotion = window.matchMedia('(prefers-reduced-motion: reduce)').matches;

  function applyTheme(theme) {
    root.setAttribute('data-theme', theme);
    toggleBtn.innerHTML = theme === 'light'
      ? '<i class="fa-solid fa-moon"></i>'
      : '<i class="fa-solid fa-sun"></i>';
    toggleBtn.setAttribute('aria-label', theme === 'light' ? 'Switch to night mode' : 'Switch to day mode');
  }

  // default: follow system preference on first load (no storage used)
  var prefersLight = window.matchMedia('(prefers-color-scheme: light)').matches;
  applyTheme(prefersLight ? 'light' : 'dark');

  toggleBtn.addEventListener('click', function () {
    var current = root.getAttribute('data-theme');
    applyTheme(current === 'light' ? 'dark' : 'light');
  });

  // ---------- 3D tilt effect ----------
  var card = document.getElementById('tiltCard');

  if (card && !reduceMotion) {
    card.addEventListener('mousemove', function (e) {
      var rect = card.getBoundingClientRect();
      var x = e.clientX - rect.left;
      var y = e.clientY - rect.top;
      var rotateX = ((y / rect.height) - 0.5) * -12;
      var rotateY = ((x / rect.width) - 0.5) * 12;
      card.style.transform =
        'perspective(900px) rotateX(' + rotateX + 'deg) rotateY(' + rotateY + 'deg)';
    });

    card.addEventListener('mouseleave', function () {
      card.style.transform = 'perspective(900px) rotateX(0deg) rotateY(0deg)';
    });
  }
})();