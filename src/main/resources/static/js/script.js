console.log('script loaded');

let changeThemeButton = document.querySelector('#theme_change_btn');

let currentTheme = getTheme();

document.addEventListener('DOMContentLoaded', (event) => {
  changeTheme();
});

function changeTheme() {
  console.log(currentTheme);
  // set theme to web page
  document.querySelector('html').classList.add(currentTheme);

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
    if (theme == 'light') {
      changeThemeButton.querySelector('span').textContent = 'Dark';
    } else {
      changeThemeButton.querySelector('span').textContent = 'Light';
    }
    return theme;
  } else {
    changeThemeButton.querySelector('span').textContent = 'Dark';
    return 'light';
  }
}
