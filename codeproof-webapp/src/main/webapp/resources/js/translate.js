/**
 * This files should contains all the text for I18n.
 */

app.config(function($translateProvider) {
	/*$translateProvider.translations('en-us', {
		WELCOME : 'Welcome, ',
		INTRO_TEXT : 'And it has i18n support!'
	}).tran;*/

	//$translateProvider.useLocalStorage();
	 
	$translateProvider.useStaticFilesLoader({
		  prefix: 'static/resources/languages/',
		  suffix: '.json'
		});
	$translateProvider.preferredLanguage('en-us');
});
