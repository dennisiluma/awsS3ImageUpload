import '../style/App.css';
import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import { useDropzone } from 'react-dropzone'


/** This(UserProfile) is a functional component which is being rendered in the App component below */
const UserProfile = () => {
  const [userprofiles, setUserProfiles] = useState([]);

  const fetchUserProfile = () => {
    axios.get('http://localhost:8080/userprofile.com').then(res => {

      const data = res.data;
      setUserProfiles(data);
    });
  }
  useEffect(() => {
    fetchUserProfile();
  }, []);

  return userprofiles.map((item, index) => {
    return (
      <div key={index} className="userprofile">
        <br />
        <br />
        <h1>{item.username}</h1>
        <p>{item.userProfileId}</p>
        <MyDropzone userProfileId = {item.userProfileId} /**same as using destructuring i.e <MyDropzone {...item}/> */ />
        <br />
      </div>
    );
  });
};

/** MyDropzone component which is rendered on line 25 above  */
function MyDropzone({userProfileId}) { //here, the userProfileId is passed as a prop from the UserProfile component on line 29
  const onDrop = useCallback(acceptedFiles => {
    const file = acceptedFiles[0];
    console.log(file);
    const formData = new FormData();
    formData.append('file', file);

    axios.post(
      `http://localhost:8080/userprofile.com/${userProfileId}/upload`,
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then((res) => {
        console.log("file upload successful");
      }).catch((err) => {
        console.log(err + "file upload failed");
      });

  }, [])
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop })

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop an Image file here ...</p> :
          <p>Drag and drop an image files here, or click to choose an image file</p>
      }
    </div>
  )
}


function App() {
  return (
    <div className="App">
      <UserProfile />
    </div>
  );
}

export default App;
