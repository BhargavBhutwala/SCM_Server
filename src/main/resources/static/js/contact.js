const baseUrl = 'http://localhost:8080';

// set the modal menu element
const viewContactModal = document.getElementById('view_contact_modal');

// options with default values
const options = {
  placement: 'bottom-right',
  backdrop: 'dynamic',
  backdropClasses: 'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
  closable: true,
  onHide: () => {
    console.log('modal is hidden');
  },
  onShow: () => {
    console.log('modal is shown');
  },
  onToggle: () => {
    console.log('modal has been toggled');
  },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_modal',
  override: true,
};

// create a new instance of the modal
const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal() {
  contactModal.show();
}

function closeContactModal() {
  contactModal.hide();
}

function loadContactData(id) {
  //   console.log(id);
  // fetch contact data from server using id
  fetch(`${baseUrl}/user/contacts/view/${id}`)
    .then(async (response) => {
      let data = await response.json();
      console.log(data);
      document.getElementById('contact_name').innerHTML = data.name;
      document.getElementById('contact_email').innerHTML = data.email;
      document.getElementById('contact_phone').innerHTML = data.phoneNumber;
      document.getElementById('contact_address').innerHTML = data.address;
      document.getElementById('contact_description').innerHTML =
        data.description;
      document.getElementById('contact_website').href =
        data.socialLinks[0].link;
      document.getElementById('contact_website').innerHTML =
        data.socialLinks[0].link;
      document.getElementById('contact_linkedIn').href =
        data.socialLinks[1].link;
      document.getElementById('contact_linkedIn').innerHTML =
        data.socialLinks[1].link;

      const contactPicture = document.getElementById('contact_picture');
      contactPicture.src = data.picture
        ? data.picture
        : 'https://upload.wikimedia.org/wikipedia/commons/2/2c/Default_pfp.svg';

      const favouriteContact = document.getElementById('contact_favorite');
      if (data.favorite) {
        favouriteContact.innerHTML =
          '<i class="fa-solid fa-star text-yellow-500"></i>';
      } else {
        favouriteContact.innerHTML = '';
      }
      // return data;
      openContactModal();
    })
    .catch((error) => {
      console.error('Error:', error);
    });
}

function deleteContact(id) {
  // Swal.fire('SweetAlert2 is working!' + id);
  Swal.fire({
    icon: 'warning',
    title: 'Do you want to delete the contact?',
    showCancelButton: true,
    confirmButtonText: 'Yes',
    cancelButtonText: 'No',
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonTextColor: '#3085d6',
    cancelButtonTextColor: '#d33',
  }).then((result) => {
    if (result.isConfirmed) {
      // Swal.fire('Saved!', '', 'success');
      const url = `${baseUrl}/user/contacts/delete/` + id;
      window.location.replace(url);
    }
  });
}
