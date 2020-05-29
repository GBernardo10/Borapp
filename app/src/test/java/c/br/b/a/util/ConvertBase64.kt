package c.br.b.a.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

public class ConvertBase64 {
    companion object {
        fun convertToBase64(image: Bitmap): String {
            val byteArrayOutputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
        }

        fun convertToBitmap(string64: String): Bitmap {
            val decodeBytes = Base64.decode(
                string64.substring(string64.indexOf(",") + 1),
                Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodeBytes, 0, decodeBytes.size);
        }
    }
}