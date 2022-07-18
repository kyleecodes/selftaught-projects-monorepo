module.exports = {
  siteMetadata: {
    siteUrl: `https://www.meowlikeapiratedaycountdown.com/`,
  },
  plugins: [
    {
      resolve: 'gatsby-plugin-html-attributes',
      options: {
        lang: 'en',
        title: 'Meow Like a Pirate Countdown'
      }
    },
    {
      resolve: `gatsby-source-filesystem`,
      options: {
        name: `images`,
        path: `${__dirname}/src/images`,
      },
    },
    `gatsby-plugin-preact`,
    `gatsby-plugin-react-helmet`,
    'gatsby-plugin-robots-txt',
    `gatsby-plugin-advanced-sitemap`,
    `gatsby-transformer-sharp`,
    `gatsby-plugin-sharp`,
    {
      resolve: `gatsby-plugin-manifest`,
      options: {
        name: `Meow like a Pirate Day Countdown`,
        short_name: `meowlikeapirate`,
        start_url: `/`,
        icon: `src/images/favicon.png`,
      },
    },
  ]
}
