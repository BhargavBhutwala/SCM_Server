console.log('script loaded');

let currentTheme = getTheme();

document.addEventListener('DOMContentLoaded', (event) => {
  changeTheme();
});

function changeTheme() {
  console.log(currentTheme);
  // set theme to web page
  document.querySelector('html').classList.add(currentTheme);

  let changeThemeButton = document.querySelector('#theme_change_btn');

  changeThemeButton.addEventListener('click', (event) => {
    //console.log('theme changed');
    let oldTheme = currentTheme;
    if (currentTheme == 'light') {
      currentTheme = 'dark';
      changeThemeButton.querySelector('span').textContent = 'Light';
    } else {
      currentTheme = 'light';
      changeThemeButton.querySelector('span').textContent = 'Dark';
    }
    setTheme(currentTheme);
    document.querySelector('html').classList.remove(oldTheme);
    document.querySelector('html').classList.add(currentTheme);
  });
}

function setTheme(theme) {
  localStorage.setItem('theme', theme);
}

function getTheme() {
  let theme = localStorage.getItem('theme');

  if (theme) {
    return theme;
  } else {
    return 'light';
  }
}
