import React from "react"
import "../components/layout.css"
import Counter from "../components/Counter"
import Layout from "../components/Layout"
import { Helmet } from "react-helmet"

export default function Home() {
  return <div>
    <Helmet>
      <meta charSet="utf-8" />
      <title>Meow Like A Pirate Day Live Countdown</title>
      <link rel="meow-like-a-pirate" href="https://www.meowlikeapiratedaycountdown.com/" />
    </Helmet>
    <Layout>
    <main>
    <div class = "counter">
        <Counter/>
      </div>
    </main>
    </Layout>
  </div>

}
