import React, { useState } from 'react'

import "../css/CreatePost.css"
import axios from 'axios'
const CreatePost = () => {

  let [formData, setFormData] = useState({
    title: '',
    content: ''
  })


  let onChangeFormData = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    })

  }
  const onClickSubmit = () => {



    // console.log("전송할 formData 확인", formData);  // ✅ 여기 꼭 찍히는지 확인
    axios.post(`${process.env.REACT_APP_API_URL}/posts`, formData)
      .then(response => {
        console.log('게시글 등록 성공:', response.data);
      })
      .catch(error => {
        console.error('게시글 등록 실패:', error);
      });
  };

  return (
    <div className='create-post-container'>
      <h1 className='create-post-title'>게시글 작성</h1>
      <form className="create-post-form">
        <div className="form-group">
          <label htmlFor="title">제목</label>
          <input
            type="text"
            id="title"
            name='title'
            placeholder='제목을 입력하세요'
            onChange={onChangeFormData}
            value={formData.title}
          />
        </div>
        <div className="form-group">
          <label htmlFor="title">내용</label>
          <textarea
            id="content"
            name='content'
            placeholder='내용을 입력하세요'
            onChange={onChangeFormData}
            value={formData.content}
          />

        </div>
        <button type="button" className="submit-button" onClick={onClickSubmit}>등록</button>
      </form>
    </div>
  )
}

export default CreatePost