import React from "react"
import logo from "../images/catbanner.png"
import { Link } from "gatsby"

const Header = ({ siteTitle }) => (
  <header
    style={{
      position: `relative`,
      display: `flex`,
      justifyContent: `space-between`,
      borderBottom: `2px solid #F1C316`,
      borderRadius: `7px`,
      paddingBottom: `10px`,
      alignContent: `center`,
      alignItems: `center`,
      width: `100%`,
    }}
  >
    <Link to="/">
      <div style={{ width: `200px`, margin: `5%` }}>
        <img src={logo} alt="meowlikeapirate-logo" />
      </div>
    </Link>
    <div style={{ maxWidth: `500px` }}>
    </div>
  </header>
)

export default Header
