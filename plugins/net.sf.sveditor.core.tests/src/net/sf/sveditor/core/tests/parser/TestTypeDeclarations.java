package net.sf.sveditor.core.tests.parser;

import net.sf.sveditor.core.SVCorePlugin;
import net.sf.sveditor.core.db.SVDBFile;
import net.sf.sveditor.core.tests.SVDBTestUtils;
import junit.framework.TestCase;

public class TestTypeDeclarations extends TestCase {
	
	public void testParameterizedFieldType() {
		String content = 
			"class t;\n" + 
			"	item_t #(P) item;\n" +
			"endclass\n"
			;
		SVCorePlugin.getDefault().enableDebug(false);
		SVDBFile file = SVDBTestUtils.parse(content, "testParameterizedFieldType");

		SVDBTestUtils.assertNoErrWarn(file);

		SVDBTestUtils.assertFileHasElements(file, "t", "item");
	}

	public void testParameterizedFieldTypeInit() {
		String content = 
			"class t;\n" + 
			"	item_t #(P) item = item_t #(P)::get();\n" +
			"endclass\n"
			;
		SVCorePlugin.getDefault().enableDebug(true);
		SVDBFile file = SVDBTestUtils.parse(content, "testParameterizedFieldTypeInit");

		SVDBTestUtils.assertNoErrWarn(file);

		SVDBTestUtils.assertFileHasElements(file, "t", "item");
	}

	public void testParameterizedFieldTypeStaticInit() {
		String content = 
			"class t;\n" + 
			"	item_t #(P) item = item_t::get();\n" +
			"endclass\n"
			;
		SVCorePlugin.getDefault().enableDebug(true);
		SVDBFile file = SVDBTestUtils.parse(content, "testParameterizedFieldTypeInit");

		SVDBTestUtils.assertNoErrWarn(file);

		SVDBTestUtils.assertFileHasElements(file, "t", "item");
	}

	public void testParameterizedFieldInit() {
		String content = 
			"class t;\n" + 
			"	function foo;\n" +
			"		int i;\n" +
			"		i = 4;\n" +
			"		item_t #(P)::item = 5;\n" +
			"	endfunction\n" +
			"endclass\n"
			;
		SVCorePlugin.getDefault().enableDebug(true);
		SVDBFile file = SVDBTestUtils.parse(content, "testParameterizedFieldTypeInit");

		SVDBTestUtils.assertNoErrWarn(file);

		SVDBTestUtils.assertFileHasElements(file, "t");
	}

	public void testAssociativeArrayInit() {
		String content = 
			"class t;\n" + 
			"	int str_int_map1[string] = '{default:null};\n" +
			"	int str_int_map2[string] = '{\"A\":5, \"B\":6};\n" +
			"endclass\n"
			;
		SVCorePlugin.getDefault().enableDebug(true);
		SVDBFile file = SVDBTestUtils.parse(content, "testAssociativeArrayInit");

		SVDBTestUtils.assertNoErrWarn(file);

		SVDBTestUtils.assertFileHasElements(file, "t", "str_int_map1", "str_int_map2");
	}

}
