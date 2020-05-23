import java.util.ArrayList;
import java.util.List;

public class Determinat_Recursive {
	
	public static long determinant(int[][] matrix) 
	{
		int order = matrix.length;//n��

		if(order < 3) return (order==2)? det_sum(matrix) : (long)matrix[0][0];	
			
		List<int[][]> first_m = new ArrayList<>();
		List<int[]>   first_c = new ArrayList<>();
		
		first_m.add(matrix);//�sn���x�}(1��int[][])
		first_c.add(matrix[0]);//�sn���x�}�Y��(1��int[])
		
		return (int)get_det(first_m,first_c);//�o�쵲�G
	}

	public static long get_det(List<int[][]> m_list,List<int[]> c_list)//���j����
	{				
		int order = m_list.get(0).length;//�o��ثe�O�X���x�}

		if(order == 2)//�p�G�ثe�O2��
		{					
			long[] array2d_t = new long[m_list.size()];//�إߤ@�Ӱ}�C(�j�p=matrix_list�j�p)�s���C�����G

			for(int z = 0; z < array2d_t.length; z++)
			{
				array2d_t[z] = det_sum(m_list.get(z));//�p��2����C��
			}	
			return get_mc_plus(c_list, array2d_t, 3, c_list.size()-1);//�o�쵲�G
		}
		
		//�p�G�ثe���O2���A�i��H�U�B�z
		int[][] temp_m = new int[order-1][order-1];//�إߤ@��n-1���x�}
		List<int[][]> ans_m = new ArrayList<>();//�t�d�s��n-1���x�}	
		List<Integer> temp_c = new ArrayList<>();//�t�d�s��n-1���x�}�Y��
		
		for(int i = 0; i < m_list.size(); i++)//i = 0 ~ �ثem_list�̪�int[][]�ƶq
		{			
			int[][] matrix = m_list.get(i);//�Nm_list�̲�i��int[][]�s�Jmatrix��
		
			for(int j = 0; j < order; j++)//j = 0 ~ �ثematrix�����h(n)��
			{
				for(int k = 0; k < order-1; k++)//k = 0 ~ �ثetemp_m�����h(n-1)��
				{
					for(int m = 0,n = 0; m < matrix.length; m++)//�B�z���i�o��
					{
						if(m==j)continue;
						
						temp_m[k][n] = matrix[k+1][m];//�Nmatrix�����̱���s��temp_m��
						n++;				
					}											
				}
				
				//�]��temp_m�|�Q�ק�A�ҥH�ƥ��ƻs�@��copy_m�A�å[��ans_m��
				int[][] copy_m = new int[temp_m.length][temp_m.length];
				
				for(int x = 0; x < temp_m.length; x++)
				{
					 for(int y = 0; y < temp_m[x].length; y++)
					 {
						 copy_m[x][y]=temp_m[x][y];
					 }					   
				}
				ans_m.add(copy_m);
		
				//�H�W�i�o��1��(n-1)���x�}copy_m
				//�H�U�Ncopy_m���x�}�Y�Ʀs��temp_c�̡Aorder=4�ɷ|�o��copy_m(3���x�})�Ptemp_c(2���Y��)
		    	if(order>3)
		    	{
		    		int[] array_c = copy_m[0];
		    		for(int v = 0; v < array_c.length;v++)
		    		{	    			
		    			temp_c.add(array_c[v]);
		    		}
		    	}    	
			}
		}
		//�H�U�Ntemp_c�s�Jc_list��
		if(order>3)
		{
			int[] array_cf = new int[temp_c.size()];
			for(int w = 0; w < array_cf.length; w++)
			{			
				array_cf[w] = temp_c.get(w);
			}			
			c_list.add(array_cf);
		}
		
		return get_det(ans_m, c_list);//�ϥλ��j�y�k�A�䤤ans_m�|��s(��l��Ʒ|��)�Bc_list�|�W�[�s���(��l����٦b)
	}
	
	public static long det_sum(int[][] matrix_2d)//�p��2����C��
	{   
		return (long)(matrix_2d[0][0]*matrix_2d[1][1])-(matrix_2d[0][1]*matrix_2d[1][0]);
	}

	public static long get_mc_plus(List<int[]> c_list, long[] array_m, int order,int c_index)//�ϥλ��j�y�k�p��X���G
	{   
		if(array_m.length==1)//�p�Garray_m�̭��u��1�ӭ�(�Y������)
		{			
			return array_m[0];
		}		
		//�p�G�̭��٦��h�ӭȡA�i��H�U�B�z
		int wantlength = c_list.get(c_index).length/order;//�o��p�⵲�G�}�C����
		long temp = 0;//�ΨӦs���C�����G(�Ĥ@����3��2���x�}�p���`�M)
		long[] ans = new long[wantlength];//�ΨӦs���C�����G(�}�C�Φ�)
		
		for(int i = 0; i < wantlength; i++)//i = 0 ~ �ثe��C�����G(�}�C�Φ�)
		{			
			for(int j = 0; j < order; j++)//j = 0 ~ order(�q3�}�l���W��n��)
			{				
				temp += c_list.get(c_index)[(order*i)+j] * array_m[(order*i)+j] * Math.pow(-1, j);
			}
			ans[i] = temp;
			temp = 0;//���mtemp
		}	
		order++;//�q3�}�l+1(��n��)
		c_index--;//�]��c_list.get(0)=��n���Y�ơA�ҥHc_index=c_list�ƶq��1�A�����0
		
		return get_mc_plus(c_list, ans, order, c_index);//�ϥλ��j�y�k�p��X���G
	}

}
