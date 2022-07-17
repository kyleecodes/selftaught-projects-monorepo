# Title     : Where to Open a Chipotle!
# Objective : Use data to discover the best place to open a new business location.
# Created by: kmfie
# Created on: 1/1/2021

# Load tidyverse, leaflet, and leaflet.extras
library(tidyverse)
library(leaflet)
library(leaflet.extras)
library(sf)

# Read datasets/chipotle.csv into a tibble named chipotle using read_csv
# "Tibbles" are modern takes on dataframes
chipotle <- read_csv("datasets/chipotle_stores.csv")

# Print out the chipotle tibble using the head function
head(chipotle)

# Create a leaflet map of all closed Chipotle stores
closed_chipotles <-
  chipotle %>%
  # Filter the chipotle tibble to stores with a value of t for closed
  filter(closed == TRUE) %>%
  leaflet() %>%
  # Use addTiles to plot the closed stores on the default Open Street Map tile
  addTiles() %>%
  # Plot the closed stores using addCircles
  addCircles()

# Print map of closed chipotles
closed_chipotles

# Use count from dplyr to count the values for the closed variable
chipotle %>%
  count(closed)

# Create a new tibble named chipotle_open that contains only open chipotle
chipotle_open <-
  chipotle %>%
  filter(closed == FALSE) %>%
  # Drop the closed column from chipotle_open
  select(-closed)

# Pipe chipotle_open into a chain of leaflet functions
chipotle_heatmap <-
chipotle_open %>%
  leaflet() %>%
  # Use addProviderTiles to add the CartoDB provider tile
  addProviderTiles("CartoDB") %>%
  # Use addHeatmap with a radius of 8
  addHeatmap(radius = 8)

# Print heatmap
chipotle_heatmap

# Create a new tibble called chipotles_by_state to store the results
chipotles_by_state <-
  chipotle_open %>%
  # Filter the data to only Chipotles in the United States
  filter(ctry == "United States") %>%
  # Count the number of stores in chipotle_open by st
  count(st) %>%
  # Arrange the number of stores by state in ascending order
  arrange(n)

# Print the state counts
chipotles_by_state

# Print the state.abb vector
state.abb

# Use the %in% operator to determine which states are in chipotles_by_state
state.abb %in% chipotles_by_state$st

# Use the %in% and ! operators to determine which states are not in chipotles_by_state
!state.abb %in% chipotles_by_state$st

# Create a states_wo_chipotles vector
states_wo_chipotles <- state.abb[!state.abb %in% chipotles_by_state$st]

# Print states with no Chipotles
states_wo_chipotles

# Load south_dakota_pop.rds into an object called south_dakota_pop
south_dakota_pop <- readRDS("datasets/south_dakota_pop.rds")

# Create color palette to color map by county population estimate
pal <- colorNumeric(palette = "viridis", domain = range(south_dakota_pop$estimate))

sd_pop_map <-
  south_dakota_pop %>%
  leaflet() %>%
  addProviderTiles("CartoDB") %>%
  # Add county boundaries with addPolygons and color by population estimate
  addPolygons(stroke = FALSE, fillOpacity = 0.7, color = ~ pal(estimate), label = ~NAME) %>%
  # Add a legend using addLegend
  addLegend(pal = pal, values = ~estimate, title = "Population")

# Print map of South Dakota population by county
sd_pop_map

# Load chipotle_sd_locations.csv that contains proposed South Dakota locations
chipotle_sd_locations <- read_csv("datasets/chipotle_sd_locations.csv")

# limit chipotle store data to locations in states boardering South Dakota
chipotle_market_research <-
  chipotle_open %>%
  filter(st %in% c("IA", "MN", "MT", "ND", "NE", "WY")) %>%
  select(city, st, lat, lon) %>%
  mutate(status = "open") %>%
  # bind the data on proposed SD locations onto the open store data
  bind_rows(chipotle_sd_locations)

# print the market research data
chipotle_market_research

# Create a blue and red color palette to distinguish between open and proposed stores
pal <- colorFactor(palette = c("Blue", "Red"), domain = c("open", "proposed"))

# Map the open and proposed locations
sd_proposed_map <-
  chipotle_market_research %>%
  leaflet() %>%
  # Add the Stamen Toner provider tile
  addProviderTiles("Stamen.Toner") %>%
  # Apply the pal color palette
  addCircles(color = ~pal(status)) %>%
  # Draw a circle with a 100 mi radius around the proposed locations
  addCircles(data = chipotle_sd_locations, radius = 100 * 1609.34, color = ~pal(status), fill = FALSE)

# Print the map of proposed locations
sd_proposed_map

# load the Voronoi polygon data
polys <- readRDS("datasets/voronoi_polygons.rds")

voronoi_map <-
  polys %>%
  leaflet() %>%
  # Use the CartoDB provider tile
  addProviderTiles("CartoDB") %>%
  # Plot Voronoi polygons using addPolygons
  addPolygons(fillColor = ~pal(status), weight = 0.5, color = "black") %>%
  # Add proposed and open locations as another layer
  addCircleMarkers(data = chipotle_market_research, label = ~city, color = ~pal(status))

# # Print the Voronoi map
# voronoi_map

# # Where should the next Chipotle store be?
# next_chipotle <- tibble(location = c("Rapid City, SD", "Sioux Falls, SD"),
#                         open_new_store = c(TRUE, FALSE))