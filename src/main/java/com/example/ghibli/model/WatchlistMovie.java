package com.example.ghibli.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "watchlist")
	public class WatchlistMovie {

	    @Id
	    private String id;

	    private String movieId;
	    private String title;
	    private String image;
	    private String userEmail;
	    private LocalDateTime addedAt;
		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}
		/**
		 * @return the movieId
		 */
		public String getMovieId() {
			return movieId;
		}
		/**
		 * @param movieId the movieId to set
		 */
		public void setMovieId(String movieId) {
			this.movieId = movieId;
		}
		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}
		/**
		 * @param title the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		/**
		 * @return the image
		 */
		public String getImage() {
			return image;
		}
		/**
		 * @param image the image to set
		 */
		public void setImage(String image) {
			this.image = image;
		}
		/**
		 * @return the userEmail
		 */
		public String getUserEmail() {
			return userEmail;
		}
		/**
		 * @param userEmail the userEmail to set
		 */
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		/**
		 * @return the addedAt
		 */
		public LocalDateTime getAddedAt() {
			return addedAt;
		}
		/**
		 * @param addedAt the addedAt to set
		 */
		public void setAddedAt(LocalDateTime addedAt) {
			this.addedAt = addedAt;
		}
	
	
}
