package wasdev.sample.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import com.ibm.watson.developer_cloud.language_translator.v2.util.Language;

public class Traductor {
	public static String translate(String word) {
		
		LanguageTranslator service = new LanguageTranslator();
		service.setUsernameAndPassword("61731114-4684-4ba8-b457-d6963b64eea0", "3RhVVBeal0ZK");

		TranslateOptions translateOptions = new TranslateOptions.Builder()
		    .addText(word).source(Language.SPANISH).target(Language.ENGLISH).build();
		TranslationResult translationResult = service.translate(translateOptions).execute();
		System.out.println(translationResult);
		
		String translationJson = translationResult.toString();
		JsonParser jsonParser = new JsonParser();
		JsonObject rootObject = jsonParser.parse(translationJson).getAsJsonObject();
		//String wordCount = rootObject.get("word_count").getAsString();
		JsonArray traducciones = rootObject.getAsJsonArray("translations");
		String traduccionPrimera = word;
		if(traducciones.size() > 0)
			traduccionPrimera = traducciones.get(0).getAsJsonObject().get("translation").getAsString();
		
		return traduccionPrimera;
	}
}
