import React, { useEffect, useState, useContext } from "react";
import {getLoggedInUser } from "../services/AuthService";
import {
  getBug,
  saveBug,
  updateBug,
  uploadImage,
  saveBugWithImages,
  updateBugWithImages,
  getBugImages,
  addBugComment,
  getBugComments,
} from "../services/Bugservice";
import { getAllUsers, getAllSprints } from "../services/Bugservice";
import { useNavigate, useParams } from "react-router-dom";
import { NotificationContext } from "../components/NotificationContext"; // Import NotificationContext

const BugComponent = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [completed, setCompleted] = useState(false);
  const [fromDate, setFromDate] = useState("");
  const [toDate, setToDate] = useState("");
  const [severity, setSeverity] = useState("");
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState("");
  const [sprints, setSprints] = useState([]);
  const [selectedSprint, setSelectedSprint] = useState("");
  const [userEmail, setUserEmail] = useState("");
  const [imageFiles, setImageFiles] = useState([]);
  const [imagePaths, setImagePaths] = useState([]);
  const [isLoadingImages, setIsLoadingImages] = useState(false);
  const [comments, setComments] = useState([]);
const [newComment, setNewComment] = useState("");
const [loggedInUser, setLoggedInUser] = useState(null);



  const navigate = useNavigate();
  const { id } = useParams();
  const { addNotification } = useContext(NotificationContext); // Use context

  useEffect(() => {
     const loggedInUsername = getLoggedInUser();
    console.log("Logged-in Username:", loggedInUsername);

  
    getAllUsers()
      .then((response) => {
        setUsers(response.data);
      const loggedInChars = new Set(loggedInUsername.toLowerCase().split(""));
    
    const userFound = response.data.find(user => {
        const fetchedChars = new Set(user.username.toLowerCase().split(""));
        
       
        return [...loggedInChars].every(char => fetchedChars.has(char));
    });        if (userFound) {
            setLoggedInUser(userFound.id); 
            console.log("Resolved User ID:", userFound.id);
        } else {
            console.warn("Logged-in user not found in user list!");
        }
      })
      .catch((error) => console.error("Error fetching users:", error));
    getAllSprints()
      .then((response) => setSprints(response.data))
      .catch((error) => console.error("Error fetching sprints:", error));
   if (id) {
     fetchComments(); 
        getBugComments(id)
            .then((response) => setComments(response.data))
            .catch((error) => console.error("Error fetching comments:", error));
    }
    if (id) {
      setIsLoadingImages(true);

      getBug(id)
        .then((response) => {
          setTitle(response.data.title || "");
          setDescription(response.data.description || "");
          setCompleted(response.data.completed || false);
          setFromDate(
            response.data.fromDate
              ? new Date(response.data.fromDate).toISOString().split("T")[0]
              : ""
          );
          setToDate(
            response.data.toDate
              ? new Date(response.data.toDate).toISOString().split("T")[0]
              : ""
          );
         

          setSeverity(response.data.severity || "");
          setSelectedUser(response.data.userId || "");
          setSelectedSprint(response.data.sprintId || "");
          setUserEmail(response.data.userEmail || "");

          return getBugImages(id);
        })
        .then((imageResponse) => {

          if (imageResponse.length > 0) {
            const formattedImagePaths = imageResponse.map((path) =>
              path.startsWith("http")
                ? path
                : `http://localhost:8082/uploads/images/${path}`
            );
            setImagePaths(formattedImagePaths);
          } else {
            setImagePaths([]);
          }
        })
        .finally(() => setIsLoadingImages(false));
    }
  }, [id]);

  function handleSprintChange(e) {
    const sprintId = Number(e.target.value);
    setSelectedSprint(sprintId);

    // Find the selected sprint and update dates
    const selectedSprintObj = sprints.find((sprint) => sprint.id === sprintId);
    if (selectedSprintObj) {
      const formatDate = (dateString) => {
    if (!dateString) return "";

    try {
        const date = new Date(dateString);
        if (isNaN(date.getTime())) {
            console.error(`Invalid date format: ${dateString}`);
            return "";
        }
   
        date.setMinutes(date.getMinutes() - date.getTimezoneOffset());
        const year = date.getFullYear();
        const month = String(date.getMonth()+1 ).padStart(2, '0'); 
        const day = String(date.getDate()).padStart(2, '0'); 
        return `${year}-${month}-${day}`; 
    } catch (error) {
        console.error(`Error parsing date: ${dateString}`, error);
        return "";
    }
};
      setFromDate(formatDate(selectedSprintObj.startDate));
      setToDate(formatDate(selectedSprintObj.endDate));
    }
  }
 const handleAddComment = async () => {
  if (!newComment.trim()) {
    console.warn("Empty comment, skipping submission.");
    return;
  }

  if (!loggedInUser) {
    console.error("No logged-in user found, cannot submit comment!");
    return;
  }

  console.log("Sending Comment:", { bugId: id, userId: loggedInUser, commentText: newComment });

  try {
    await addBugComment(id, loggedInUser, newComment);
    fetchComments(); // Refresh comments
    setNewComment(""); // Clear input
  } catch (error) {
    console.error("Error adding comment:", error.response?.data || error);
  }
};



const fetchComments = async () => {
    console.log("Fetching comments for bug ID:", id);

    try {
        const response = await getBugComments(id);
        console.log("Fetched Comments Response:", response);

   
        setComments(Array.isArray(response) ? response : []);
        
        console.log("Updated Comments State:", response);
    } catch (error) {
        console.error("Error fetching comments:", error.response?.data || error);
        setComments([]); 
    }
};



  const handleFileChange = (e) => {
    setImageFiles(Array.from(e.target.files));
  };
  function saveOrUpdateBug(e) {
    e.preventDefault();

    if (
      !title ||
      !description ||
      !fromDate ||
      !toDate ||
      !severity ||
      !selectedUser ||
      !selectedSprint
    ) {
      alert("All fields are required.");
      return;
    }

    const selectedUserObject = users.find((user) => user.id === selectedUser);
    const emailToSend = selectedUserObject?.email || userEmail;

    const bug = {
      title,
      description,
      completed,
      fromDate,
      toDate,
      severity: severity.toUpperCase(),
      userId: selectedUser,
      sprintId: selectedSprint,
      userEmail: emailToSend,
    };

    if (id) {
      updateBug(id, bug)
        .then(() => {
          addNotification(
            `Email sent to ${selectedUserObject.email}`,
            selectedUserObject.username
          );
          if (imageFiles.length > 0) {
            return Promise.all(
              imageFiles.map((file) => {
                const formData = new FormData();
                formData.append("file", file);
                return uploadImage(id, formData); 
              })
            );
          }
          return Promise.resolve();
        })
        .then(() => updateBugWithImages(id, bug, imageFiles)) 
       .then(() => navigate("/bugs"))
        .catch((error) => {
          console.error("Error updating bug:", error);
        
        if (error.response && error.response.status > 400) {
            addNotification(
                `No email sent to ${selectedUserObject.email}`,
                selectedUserObject.username
            );
        }
         
        });
    } else {
      saveBug(bug)
        .then((response) => {
          const bugId = response.data.id;
          addNotification(
            `New bug assigned to ${selectedUserObject.username}. Email notification sent to ${selectedUserObject.email}`,
            selectedUserObject.username
          );

          if (imageFiles.length > 0) {
            return Promise.all(
              imageFiles.map((file) => {
                const formData = new FormData();
                formData.append("file", file);
                return uploadImage(bugId, formData); 
              })
            ).then(() => bugId);
          }
          return Promise.resolve(bugId);
        }) .then(() => navigate("/bugs"))
        .then((bugId) => saveBugWithImages(bugId, bug, imageFiles)) 
       
         .catch((error) => {
        console.error("Error saving bug:", error);

        if (error.response && error.response.status > 400) {
            addNotification(
                `No email sent to ${selectedUserObject.email}`,
                selectedUserObject.username
            );
        }

     
    });
    }
  }

  return (
    <div
      className="container"
      style={{
        marginLeft: "7%",
        maxWidth: "85%",
        overflowY: "auto",
        height: "100vh",
        marginBottom: "50px",
        marginTop: "10px",
      }}
    >
      <div className="row">
        <div
          className="card col-md-6 offset-md-3"
          style={{ paddingBottom: "20px" }}
        >
          <h2 className="text-center">{id ? "Update Bug" : "Add Bug"}</h2>
          <div className="card-body">
            <form>
              <div className="form-group mb-2">
                <label className="form-label">Bug Title:</label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter Bug Title"
                  value={title}
                  onChange={(e) => setTitle(e.target.value)}
                />
              </div>
              <div className="form-group mb-2">
                <label className="form-label">Bug Description:</label>
                <textarea
                  className="form-control"
                  placeholder="Enter Bug Description"
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
                  rows="6"
                >
                  {" "}
                </textarea>
              </div>
              <div className="form-group mb-2">
                <label className="form-label">Bug Completed:</label>
                <select
                  className="form-control"
                  value={completed}
                  onChange={(e) => setCompleted(e.target.value === "true")}
                >
                  <option value="false">No</option>
                  <option value="true">Yes</option>
                </select>
              </div>
              <div className="form-group mb-2">
                <label className="form-label">Sprint</label>
                <select
                  className="form-control"
                  value={selectedSprint}
                  onChange={handleSprintChange}
                >
                  <option value="">Select Sprint</option>
                  {sprints.map((sprint) => (
                    <option key={sprint.id} value={sprint.id}>
                      {sprint.sprintName}
                    </option>
                  ))}
                </select>
              </div>

              <div className="form-group mb-2">
                <label className="form-label">From Date:</label>
                <input
                  type="date"
                  className="form-control"
                  value={fromDate}
                  onChange={(e) => setFromDate(e.target.value)}
                />
              </div>
              <div className="form-group mb-2">
                <label className="form-label">To Date:</label>
                <input
                  type="date"
                  className="form-control"
                  value={toDate}
                  onChange={(e) => setToDate(e.target.value)}
                />
              </div>
              <div className="form-group mb-2">
                <label className="form-label">Bug Severity:</label>
                <select
                  className="form-control"
                  value={severity}
                  onChange={(e) => setSeverity(e.target.value)}
                >
                  <option value="">Select Severity</option>
                  <option value="LOW">Low</option>
                  <option value="MEDIUM">Medium</option>
                  <option value="HIGH">High</option>
                  <option value="CRITICAL">Critical</option>
                </select>
              </div>

              <div className="form-group mb-3">
                <label className="form-label">Assign User:</label>
                <select
                  className="form-control"
                  value={selectedUser}
                  onChange={(e) => setSelectedUser(Number(e.target.value))}
                >
                  <option value="">Select User</option>
                  {users.map((user) => (
                    <option key={user.id} value={user.id}>
                      {user.username}
                    </option>
                  ))}
                </select>
              </div>
              <div>
                <div className="image-preview-container">
                  {imagePaths.length > 0 ? (
                    imagePaths.map((path, index) => (
                      <img
                        key={index}
                        src={path}
                        alt="Bug Image"
                        className="thumbnail"
                        onError={(e) => {
                          console.error(`Image failed to load: ${path}`);
                          e.target.style.display = "none"; 
                        }}
                      />
                    ))
                  ) : (
                    <><div style={{ height: "16px" }} /><p>No images available for this bug</p></>
                  )}
                </div>
              </div>

              <div className="form-group mb-3">
                <label className="form-label">Upload Images:</label>
                <input
                  type="file"
                  multiple
                  accept="image/*"
                  className="form-control"
                  onChange={handleFileChange}
                />
              </div>
<div className="comments-list">
    {Array.isArray(comments) && comments.length > 0 ? (
        comments.map((comment) => (
            <div key={comment.id} className="comment-item">
                <p><strong>{comment.user?.name || "Unknown"}</strong>: {comment.comment}</p>
                <p className="comment-date">
                    Posted on: {comment.createdAt ? new Date(...comment.createdAt).toLocaleString() : "Timestamp missing"}
                </p>
            </div>
        ))
    ) : (
        <p>No comments found for this bug.</p>
    )}
</div>

<div className="form-group mb-2">
    <label htmlFor="commentBox" className="form-label">Comments:</label>
    <textarea
        id="commentBox"
        className="form-control"
        value={newComment}
        onChange={(e) => setNewComment(e.target.value)}
        placeholder="Add a comment..."
        rows="4" 
        required 
    ></textarea>
    <button 
        className="btn btn-success btn-sm mt-2" 
        onClick={handleAddComment} 
        disabled={!newComment.trim()}
    >
        Comment
    </button>
</div>



              <div className="form-group mt-4">
                <button
                  className="btn btn-success btn-lg"
                  style={{ fontSize: "15px" }}
                  onClick={(e) => saveOrUpdateBug(e)}
                >
                  Submit
                </button>
              </div>
            </form>

 

          </div>
        </div>
      </div>
    </div>
  );
};

export default BugComponent;
