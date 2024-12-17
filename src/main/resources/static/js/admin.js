console.log('admin loaded');

document.querySelector('#picture').addEventListener('change', function (e) {
  let file = e.target.files[0];
  let reader = new FileReader();

  reader.onload = function (event) {
    document
      .querySelector('#upload_image_preview')
      .setAttribute('src', reader.result);
  };

  reader.readAsDataURL(file);
});
